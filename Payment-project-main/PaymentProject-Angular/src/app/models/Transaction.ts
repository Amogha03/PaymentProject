import { Bank } from "./bank";
import { Customer } from "./customer";
import { Message } from "./message";
import { TransferType } from "./TransferType";

export class Transaction{
    constructor(public customer: Customer,
        public message: Message,
        public receiveraccounholdernumber: Customer,
        public receiveraccountholdername: string,
        public receiverbic: Bank,
        public senderbic: Bank,
        public transactionid: number,
        public transferDate: Date,
        public transferfees: number,
        public currencyamount: number,
        public transfertypecode: TransferType){

    }
}