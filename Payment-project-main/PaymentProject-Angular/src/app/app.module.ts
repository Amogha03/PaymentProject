import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { NavitemsComponent } from './navitems/navitems.component';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule } from '@angular/common/http';
import { TransferComponent } from './transfer/transfer.component';
import { DropdownComponent } from './dropdown/dropdown.component';
import { Message } from './models/message';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TopcustomersComponent } from './dashboard/topcustomers/topcustomers.component';
import { TransactionhistoryComponent } from './dashboard/transactionhistory/transactionhistory.component';

const appRoutes = [
  
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavitemsComponent,
    LoginComponent,
    TransferComponent,
    DropdownComponent,
    DashboardComponent,
    TopcustomersComponent,
    TransactionhistoryComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [HeaderComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
