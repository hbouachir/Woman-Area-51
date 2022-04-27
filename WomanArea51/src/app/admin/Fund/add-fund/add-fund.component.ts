import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { fund } from 'src/app/Model/fund';
import { FundCat } from 'src/app/Model/FundCat';
import { FundService } from 'src/app/_services/Fund/fund.service';
import { FundCatService } from 'src/app/_services/FundCategory/fund-cat.service';

@Component({
  selector: 'app-add-fund',
  templateUrl: './add-fund.component.html',
  styleUrls: ['./add-fund.component.css']
})
export class AddFundComponent implements OnInit {
  id:number;
  c:FundCat;
  f:fund=new fund();
  tags:string[];
  added:string[]=[];
  selected:string[]=[];
  t:string=null;
  constructor(private _Activatedroute:ActivatedRoute,private _router:Router, private FCS:FundCatService, private FS:FundService) { }


  addFund(f:fund){
    if (this.added!=[] && this.selected!=[]  ){
      f.tags=this.selected.concat(this.added);
    }
    else if(this.added!=[]  && this.selected==[]  ){
      f.tags=this.added;
    }
    else f.tags=this.selected;
    
    this.FS.addfund(f,this.id).subscribe(()=>this._router.navigateByUrl("/admin/Fund/showAll"));

  }
  GetFundTags(){
    this.FS.FundTags().subscribe(res => {
      console.log(res);
      this.tags=res;
    });

  }

  addNewTag(t:string){
    this.added.push(t);
    this.t=null;
    console.log(this.added);
  }


  ngOnInit(): void {
    this.id=Number(this._Activatedroute.snapshot.paramMap.get("id"));
    if(this.id!=null){
      this.FCS.findCatById(this.id).subscribe(res => {
        console.log(res);
        this.c=res;
        
      });
  }

  this.FS.FundTags().subscribe(res => {
    console.log(res);
    this.tags=res;
  });
}
}
