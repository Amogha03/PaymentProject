import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {url} from '../constants'

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {

  constructor(private http: HttpClient) {

  }

  headerDict={
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Allow-Origin': '*'
  }

  requestOptions ={
    headers: new HttpHeaders(this.headerDict)
  }

  checkAuth(data: any){

     return this.http.post(url+'authenticate',data);

  }

  isauthenticate() {

    let user = sessionStorage.getItem('token')
    return user!==null

  }


}
