import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  token = sessionStorage.getItem('token')

  itemslist: any = [
   
    {
      name: 'Transfer',
      link: 'transfer'
    },
    {
      name: 'Dashboard',
      link: 'dashboard'
    }
  ]
  constructor(private router: Router) { 
   }

  ngOnInit(): void {
  }

  onLogout(){
    sessionStorage.removeItem('token')
   
    // this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    //   this.router.navigate(['/login']);
    // });

    this.router.navigate(['/login'])
  }

}
