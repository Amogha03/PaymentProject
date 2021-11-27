import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {url } from '../constants'

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  headerDict={
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Allow-Origin': '*',
    'Authorization': 'Bearer '+sessionStorage.getItem('token')
  }

  headers =  new HttpHeaders(this.headerDict);

  getCustomer() {
    return this.http.get(url+'customeruser/getuser', {headers: this.headers})
  }

  updateCustomre(data: any) {
    return this.http.patch(url+'customers',data,{headers: this.headers})
  }


}
