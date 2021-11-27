import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {url} from '../constants'
import { Transaction } from '../models/Transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http: HttpClient) { }

  headerDict={
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Allow-Origin': '*',
    'Authorization': 'Bearer '+sessionStorage.getItem('token')
  }

  headers =  new HttpHeaders(this.headerDict);

  getTransferFee(){
    return this.http.get<Number>('http://127.0.0.1:8080/transaction/transfercharges',{headers: this.headers})
  }

  makeTransaction(data: any){

    console.log('hello')

    return this.http.post(url+'transaction',data, {headers: this.headers})
  }

  checkEligibility(sendercustomerid: string, receivercustomerid: string){
    // let params = new HttpParams();
    // params = params.append('sendercustomerid',sendercustomerid)
    // params = params.append('receivercustomerid',receivercustomerid)
    return this.http.get(url+'transaction/eligibilitycheck/'+sendercustomerid+'/'+receivercustomerid,{headers: this.headers})
  }


  gettranshistory(customerid: string): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(url+'transaction/gettransaction/'+customerid,{headers: this.headers})
  }
}
