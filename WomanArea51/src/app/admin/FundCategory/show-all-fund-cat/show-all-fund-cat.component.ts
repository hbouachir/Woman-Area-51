import { Component, OnInit } from '@angular/core';
import { FundCat } from 'src/app/Model/FundCat';
import { FundCatService } from 'src/app/_services/FundCategory/fund-cat.service';

@Component({
  selector: 'app-show-all-fund-cat',
  templateUrl: './show-all-fund-cat.component.html',
  styleUrls: ['./show-all-fund-cat.component.css']
})
export class ShowAllFundCatComponent implements OnInit {

  listFundCat:FundCat[];
  fc:FundCat;
  id:number;
  fcid:number;
  keyword:string;
  search:boolean;

  constructor(private FCS:FundCatService) { }
  deleteFundCateById(id){
    this.FCS.deleteFundCateById(id).subscribe(()=>this.FCS.getFundCats().subscribe((() => this.FCS.getFundCats()
    .subscribe(res => { this.listFundCat = res }))));
    

  }
  SearchCatById(id :number){
    if(id!=null){
      this.FCS.findCatById(id).subscribe(res => {
        console.log(res);
        this.search=true;
        this.fc=res;
        this.listFundCat=[];
        this.listFundCat.push(res);
        
      })}
      if(id==null){
        this.FCS.getFundCats().subscribe(res => {
          console.log(res);
          this.search=false;
          this.listFundCat = res;

      });
    }
  }
  
  ngOnInit(): void {
    this.FCS.getFundCats().subscribe(res => {
      console.log(res);
      this.search=false;
      this.listFundCat = res
    });

  }

}
