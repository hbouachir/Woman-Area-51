import { Component, OnInit } from '@angular/core';
import { fund } from 'src/app/Model/fund';
import { FundService } from 'src/app/_services/Fund/fund.service';

@Component({
  selector: 'app-show-all-funds',
  templateUrl: './show-all-funds.component.html',
  styleUrls: ['./show-all-funds.component.css']
})
export class ShowAllFundsComponent implements OnInit {

  constructor(private FS:FundService) { }

  FundsList:fund[];
  SearchFunds:fund[];
  status:string;
  list:fund[]=[];
  fid:number;
  search:boolean;
  fund:fund;
  keyword:string;
  tags:string[];

  findFundById(id:number){
    if(id!=null){
      this.FS.findFundById(id).subscribe(res => {
        console.log(res);
        this.search=true;
        this.FundsList=[];
        this.FundsList.push(res);
      })}
      if(id==null) {
        this.FS.getfunds().subscribe(res => {
          console.log(res);
          this.search=false;
          this.FundsList = res;
      });
    }
    
  }

  DisplayTags(f:fund){
    this.tags=f.tags;

  }
  statusReport(goal:number, raised:number){
    if (goal==raised)
    return "Goal reached"
    else return "Pending"
  }

  DeleteFund(id:number){
    this.FS.deletefundeById(id).subscribe(()=>this.FS.getfunds().subscribe((() => this.FS.getfunds()
    .subscribe(res => { this.FundsList = res }))));
    


  }
  ngOnInit(): void {

    this.FS.getfunds().subscribe(res => {
      console.log(res);
      this.search=false;
      this.FundsList = res
      
    });

  }

}
