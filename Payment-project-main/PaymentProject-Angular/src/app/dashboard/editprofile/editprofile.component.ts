import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent implements OnInit {

  profile?: Customer;
  editprofileform: FormGroup
  constructor(private customerservice: CustomerService,
    private router: Router) {
      
      this.editprofileform = new FormGroup({
        accountholdername: new FormControl('', [
          Validators.required
        ]),
        customerid: new FormControl('',[
          Validators.required
        ]),
        customeraddress: new FormControl('',[
          Validators.required
        ]),
        customercity: new FormControl('',[
          Validators.required
        ])
      })


  }

  ngOnInit(): void {

    this.customerservice.getCustomer().subscribe((data: any)=>{

      this.profile = data.customer;
      this.editprofileform.controls['customerid'].setValue(data.customer.customerid)
      this.editprofileform.controls['accountholdername'].setValue(data.customer.accountholdername)
      this.editprofileform.controls['customeraddress'].setValue(data.customer.customeraddress)
      this.editprofileform.controls['customercity'].setValue(data.customer.customercity)
      

    },(errror)=>{



    })

  }

  handleEditprocess() {
    
      this.profile!.accountholdername=this.editprofileform.controls['accountholdername'].value
      this.profile!.customerid=this.editprofileform.controls['customerid'].value
      this.profile!.customeraddress= this.editprofileform.controls['customeraddress'].value
      this.profile!.customercity=this.editprofileform.controls['customercity'].value
   

    this.customerservice.updateCustomre(this.profile).subscribe((data)=>{

         alert("Profile Updated Successfully")
        //  this.router.navigate(['../viewprofile'])

    },(error)=>{
         console.log(error)
    })
  }

}
