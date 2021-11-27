import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from 'src/app/services/dashboard.service';
import { Chart, Point } from "chart.js";

@Component({
  selector: 'app-topcustomers',
  templateUrl: './topcustomers.component.html',
  styleUrls: ['./topcustomers.component.css']
})
export class TopcustomersComponent implements OnInit {

  topcustomers?: any

  topcustomername?: any = [];
  topcustomeramount?: any = []

  constructor(private dashboardservice: DashboardService) {

  }

  ngOnInit(): void {
   
    this.getTopCustomers();

  }

  getTopCustomers() {

    this.dashboardservice.getTopCustomers().subscribe((data: any) => {

      // console.log(data)
      this.topcustomers  = data;

      data.forEach((item: any, index: any) => {
        this.topcustomername.push(item[0])
        this.topcustomeramount.push(item[1])
      })

    })
    
  }


}


