import { FundCat } from "./FundCat";

export class fund{
    fundId:number;
    fundName:string;
    goal:number;
    raised:number;
    fundDescription:string;
    lastDonation:Date;
    benficiaries:string;
    tags:string[];
    fCategory:FundCat;

}

