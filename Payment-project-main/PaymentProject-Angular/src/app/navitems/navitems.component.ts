import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navitems',
  templateUrl: './navitems.component.html',
  styleUrls: ['./navitems.component.css']
})
export class NavitemsComponent implements OnInit {

  @Input()
  item: any = {
    name: '',
    link: ''
  }
  constructor(public router: Router) { }

  ngOnInit(): void {
  }

}
