import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Bank } from '../models/bank';
import { Customer } from '../models/customer';
import { CustomerService } from '../services/customer.service';
import { MessageserviceService } from '../services/messageservice.service';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  transferForm: FormGroup
  allMessages: Message[]=[]
  sender?: Customer
  dropdown: any
  transferfees: number = 0
  message?: Message
  transfertypecode: any
  eligible: boolean = false
  constructor(private messageservice: MessageserviceService,
    private transactionservice: TransactionService, 
    private customerservice: CustomerService,
    private router: Router
    ) {
    
    this.dropdown = {
      ControlName: 'message',
      labelName: 'Message Code',
      defaultLabel: '',
      messages: [Message],
      selectedValue: ''
    }

      this.transferForm = new FormGroup({
        receiveraccounholdernumber: new FormControl('',[
          Validators.required,
          Validators.min(0)
        ]),
        receiverbic: new FormControl('',[
          Validators.required,
          Validators.pattern(/^[ A-Z0-9]*$/)
        ]),
        currencyamount: new FormControl('',[
          Validators.required,
          Validators.min(0)
        ])
         

      })
  }

  ngOnInit(): void {


    this.customerservice.getCustomer().subscribe((data: any)=>{

      this.sender = data.customer;
      

    },(errror)=>{


    })

    this.transactionservice.getTransferFee().subscribe((data)=>{

        this.transferfees = +data;       

    },(error)=>{

    })
    

    this.messageservice.getAllMessages().subscribe((data)=>{

      this.allMessages = data;
      this.dropdown.messages = data;

    },(error)=>{

       

    })

  }

  get rcustomerid(){
     return this.transferForm.controls['receiveraccounholdernumber']
  }

  get rbic(){
     return this.transferForm.controls['receiverbic']
  }

  get currencyamount(){
     return this.transferForm.controls['currencyamount']
  }

  handleTransaction(){

    let customerid: string ='';
   
    this.transactionservice.checkEligibility(this.sender?.customerid!,this.transferForm.controls['receiveraccounholdernumber'].value)
    .subscribe((data)=>{

      this.eligible = true;


    },(error)=>{

      this.eligible  = false;

      window.alert(error.error[0])


    })


    let transferdata ={
      customer: {
        customerid: this.sender?.customerid
      },
      senderbic: {
        bic: this.sender?.bank.bic
      },
      receiveraccounholdernumber: {
        customerid: this.transferForm.controls['receiveraccounholdernumber'].value
      },
      receiverbic: {
        bic: this.transferForm.controls['receiverbic'].value
      },
      message: this.message,

      currencyamount: this.transferForm.controls['currencyamount'].value
      
    }


    if(this.eligible){
      this.transactionservice.makeTransaction(transferdata).subscribe((data)=>{

        
        window.alert('Transaction successfull');

        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/transfer']);
        });
  
  
      },(error)=>{
  
         if(error.status>=400 &&error.status<500){
           window.alert(error.message)
         }
         else
          window.alert('Something went wrong. Please try after sometime')

  
      })
    }


  }


  handleChange(event: any){
      this.message = event
  }



}
