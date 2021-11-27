import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {url } from '../constants'

@Injectable({
  providedIn: 'root'
})
export class MessageserviceService {

  constructor(private http: HttpClient) { }

  headerDict={
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Allow-Origin': '*',
    'Authorization': 'Bearer '+sessionStorage.getItem('token')
  }

  requestOptions ={
    headers: new HttpHeaders(this.headerDict)
  }

  getAllMessages() {

   return this.http.get<Message[]>('http://127.0.0.1:8080/message',this.requestOptions)

  }
}
