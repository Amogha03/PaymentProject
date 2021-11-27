package com.dbs.payment.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.constants.BankContants;
import com.dbs.payment.model.Bank;
import com.dbs.payment.model.Customer;
import com.dbs.payment.model.Message;
import com.dbs.payment.model.Transaction;
import com.dbs.payment.model.TransferTypes;
import com.dbs.payment.repository.TransactionRepository;
import com.dbs.payment.service.BankService;
import com.dbs.payment.service.CustomerService;
import com.dbs.payment.service.MessageService;
import com.dbs.payment.service.TransactionService;
import com.dbs.payment.service.TransferTypesService;
import com.dbs.payment.util.Permutation;

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {

	private static boolean customertransfer = false;

	private static boolean banktransfer = false;

	@Autowired
	private BankService bankservice;

	@Autowired
	private CustomerService customerservice;

	@Autowired
	private MessageService messageservice;

	@Autowired
	private TransactionService transactionservice;

	@Autowired
	private TransferTypesService transfertypeservice;

	@Autowired
	private TransactionRepository transactionrepo;

//	@Autowired
//	private BankContants bankconstants;

	@GetMapping("/transfercharges")
	public double getTransferCharges() {
		return BankContants.getTransferCharges();
	}

	@GetMapping("/eligibilitycheck/{sendercustomerid}/{receivercustomerid}")
	public ResponseEntity<List<String>> checkEligibility(@PathVariable String sendercustomerid,
			@PathVariable String receivercustomerid) {

		List<String> list = new ArrayList();

		if (sendercustomerid.equals(receivercustomerid)) {
			list.add("You Cannot transfer to your own account, please enter other user's account");

			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(list);
		}

		char stransfertype = checkTransferType(sendercustomerid);
//		System.out.println(stransfertype);

		char rtransfertype = checkTransferType(receivercustomerid);

		if (stransfertype == 'C' && rtransfertype == 'B') {
			list.add("You cannot transfer to a Bank. Please enter Customer's customerid");

			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(list);
		} else if (stransfertype == 'B' && rtransfertype == 'C') {

			list.add("You cannot transfer to a Customer. Please enter Bank's customerid");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(list);
		} else {

			list.add("Ok");
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}

	}

//	@GetMapping("/transfertype")
	public static char checkTransferType(String customerid) {

		boolean flag = false;
		String BankTransferTypes[] = BankContants.getBank_Customer_IDS();

		for (String x : BankTransferTypes) {
			if (x.equals(customerid)) {
				flag = true;
				banktransfer = true;
				return 'B';

			}

		}

		if (!flag) {
			customertransfer = true;

			return 'C';
		}
		return 0;

	}

	public static boolean verifyinSDNlist(String name) throws FileNotFoundException {

		String[] l = name.strip().split(" ");

		List<String> permList = Permutation.findPermutations(l, ' ');
		String regexp = String.join("|", permList);
		File file = ResourceUtils.getFile("classpath:sdnlist.txt");

		Scanner fileScanner = new Scanner(file);

		boolean matchFound = false;

		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = null;
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			matcher = pattern.matcher(line);
			if (matcher.find()) {
				matchFound = true;
				break;
			}
		}

		return matchFound;

	}

	@GetMapping("/gettransaction/{customerid}")
	public List<Transaction> getTransactions(@PathVariable String customerid) {

		return transactionrepo.findByCustomerid(customerid);

	}

	@PostMapping
//	@Transactional
	public ResponseEntity<List<String>> makeTransaction(@RequestBody Transaction transaction) {

//		System.out.println(transaction.getSenderbic());
//		System.out.println(transaction);
		System.out.println("inside mt");
		List<String> list = new ArrayList();
		try {

			String scid = transaction.getCustomer().getCustomerid();

			String sbic = transaction.getSenderbic().getBic();

			String rcid = transaction.getReceiveraccounholdernumber().getCustomerid();

			String rbic = transaction.getReceiverbic().getBic();

			System.out.println(transaction);
			String mcode = transaction.getMessage().getMessagecode();

			double amount = transaction.getCurrencyamount();

			double transferfees = BankContants.calculateTransferFee(amount);
			double todebitamount = amount + transferfees;

			Customer scustomer = customerservice.getCustomerByCustomerID(scid);

			Customer rcustomer = customerservice.getCustomerByCustomerID(rcid);

			Bank sbank = bankservice.findBankNameById(sbic);

			Bank rbank = bankservice.findBankNameById(rbic);

			Message message = messageservice.findMessageByID(mcode);

			String transfertypecode = checkTransferType(scid) + "";

			TransferTypes transfertype = transfertypeservice.findTransferTypeByID(transfertypecode);

			if (scustomer != null && sbank != null && rcustomer != null && rbank != null && message != null) {

				if ((scustomer.getClearbalance() >= todebitamount || scustomer.getOverdraftflag() == 1)
						&& scustomer.getClearbalance() > 0) {

					scustomer.setClearbalance(scustomer.getClearbalance() - todebitamount);
					rcustomer.setClearbalance(rcustomer.getClearbalance() + amount);

					if (customerservice.updateCustomer(scustomer) && customerservice.updateCustomer(rcustomer)) {

						transaction.setTransfertypecode(transfertype);
						transaction.setTransferfees(transferfees);
						transaction.setTransferDate(LocalDate.now());
						transaction.setCustomer(scustomer);
						transaction.setReceiveraccounholdernumber(rcustomer);
						transaction.setReceiveraccountholdername(rcustomer.getAccountholdername());
						transaction.setSenderbic(sbank);
						transaction.setReceiverbic(rbank);
						transaction.setMessage(message);

						if (transactionservice.updateTransaction(transaction)) {

							list.add("Transaction Successfull");
							return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);

						} else {
							throw new Exception("Cannot proceed with the transaction.Please try again");
						}

					} else {
						throw new Exception("Cannot update the customers now, please try later");
					}

				}

				else {

					list.add("Insufficient Balance");
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(list);

				}

			}

			throw new IllegalArgumentException();
			// almost done only conditions need to be checked

		} catch (Exception e) {

			list.add(e.getMessage());
			System.out.println("hello error occured" + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);

		}

//		return null;

	}

}
