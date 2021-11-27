import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Bank } from 'src/app/models/bank';
import { Customer } from 'src/app/models/customer';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-viewprofile',
  templateUrl: './viewprofile.component.html',
  styleUrls: ['./viewprofile.component.css']
})
export class ViewprofileComponent implements OnInit {

  bank?: Bank;
  profile?: Customer;
  constructor(private customerservice: CustomerService) { }

  ngOnInit(): void {

    this.customerservice.getCustomer().subscribe((data: any)=>{

      this.profile = data.customer;
      

    },(errror)=>{



    })

  }

}
