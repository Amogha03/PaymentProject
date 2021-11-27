import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { url  } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) { }


  headerDict={
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Allow-Origin': '*',
    'Authorization': 'Bearer '+sessionStorage.getItem('token')
  }

  headers =  new HttpHeaders(this.headerDict);

  getTopCustomers() {

    return this.http.get(url+'customers/topcustomers',{headers: this.headers})
    
  }
}
