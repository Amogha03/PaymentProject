import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EditprofileComponent } from './dashboard/editprofile/editprofile.component';
import { TopcustomersComponent } from './dashboard/topcustomers/topcustomers.component';
import { TransactionhistoryComponent } from './dashboard/transactionhistory/transactionhistory.component';
import { ViewprofileComponent } from './dashboard/viewprofile/viewprofile.component';
import { AuthGaurd } from './Gaurds/AuthGaurd';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { TransferComponent } from './transfer/transfer.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'transfer', component: TransferComponent, canActivate: [AuthGaurd]},
  {path: 'dashboard', component: DashboardComponent,canActivate: [AuthGaurd],children:[
    {path: 'editprofile', component: EditprofileComponent},
    {path: 'viewprofile', component: ViewprofileComponent},
    {path: 'topcustomers', component: TopcustomersComponent},
    {path: 'transhistory', component: TransactionhistoryComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
