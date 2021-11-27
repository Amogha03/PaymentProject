import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer';
import { Transaction } from 'src/app/models/Transaction';
import { CustomerService } from 'src/app/services/customer.service';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transactionhistory',
  templateUrl: './transactionhistory.component.html',
  styleUrls: ['./transactionhistory.component.css']
})
export class TransactionhistoryComponent implements OnInit {


  sender?: Customer
  history?: Transaction[]

  constructor(private transservice: TransactionService,
    private customerservice: CustomerService) { }


  ngOnInit(): void {

    this.customerservice.getCustomer().subscribe((data: any)=>{

      this.sender = data.customer;

      this.getTransactionHistory();
      

    },(errror)=>{


    })

   

  }

  getTransactionHistory(){

    this.transservice.gettranshistory(this.sender?.customerid!).subscribe((data)=>{

      this.history = data;
      // console.log(this.history)
     

    },(error)=>{

    })

  }

}
