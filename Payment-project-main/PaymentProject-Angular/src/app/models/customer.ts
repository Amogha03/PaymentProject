import { Bank } from "./bank";

export class Customer{
    constructor(public customerid: string,
        public accountholdername: string,
        public overdraftflag: number,
        public clearbalance: number,
        public customeraddress: string,
        public customercity: string,
        public customertype: string,
        public bank: Bank){
    }
}