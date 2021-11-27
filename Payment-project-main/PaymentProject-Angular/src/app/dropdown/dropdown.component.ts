import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Message } from '../models/message';

@Component({
  selector: 'app-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrls: ['./dropdown.component.css']
})
export class DropdownComponent implements OnInit {


  index: any
  @Input()
  schema:any
 
  @Output()
  handlechanges: EventEmitter<any>
  constructor() {
    this.schema = {
      ControlName: '',
      labelName: '',
      defaultLabel: '',
      messages: [{messagecode: '', instruction:''}],
      selectedValue: ''
    }

    this.handlechanges = new EventEmitter<Message>();
    
   }


  ngOnInit(): void {

  }

  handleDropdownchange(event: any) {

       let i: number;
       this.schema.messages.forEach((element: any,index: any)=>{
         if(element.messagecode==event.target.value) {
           i = index;
           this.handlechanges.emit(this.schema.messages[i])
          
          }
       })
      
  }

}
