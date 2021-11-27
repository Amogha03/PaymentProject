import { Directive, ElementRef, HostListener } from "@angular/core";

@Directive({
    selector: '[positives]'
})
export class OnlyPositivies{
    constructor(private el: ElementRef){
     
    }

    @HostListener('keydown',['$event'])
    handleInput(event: any){
        if(event.key==189) return false;
        if(event.key>=0 && event.key<9){
            return true;
        }

        return false;


    }
}