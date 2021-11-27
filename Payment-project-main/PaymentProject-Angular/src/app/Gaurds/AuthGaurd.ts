import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { LoginserviceService } from "../services/loginservice.service";

@Injectable({
    providedIn: 'root'
})
export class AuthGaurd implements CanActivate{
    constructor(private loginservice: LoginserviceService, private router: Router) {

    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot)
    :boolean{

        if(this.loginservice.isauthenticate())
        return true;

        else this.router.navigate(['/login']);

        return false;

    }
}