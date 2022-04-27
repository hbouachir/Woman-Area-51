import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { fund } from 'src/app/Model/fund';
import { FundCat } from 'src/app/Model/FundCat';
import { FundService } from 'src/app/_services/Fund/fund.service';
import { FundCatService } from 'src/app/_services/FundCategory/fund-cat.service';

@Component({
  selector: 'app-edit-fund',
  templateUrl: './edit-fund.component.html',
  styleUrls: ['./edit-fund.component.css']
})
export class EditFundComponent implements OnInit {
  fId:number;
  c:FundCat;
  f:fund=new fund();
  tags:string[]=[];
  added:string[]=[];
  selected:string[]=[];
  t:string=null;
  catId:number;
  loading:string="Loading...Please hold"
  constructor(private _Activatedroute:ActivatedRoute,private _router:Router, private FCS:FundCatService, private FS:FundService) { }


  addNewTag(t:string){
    this.added.concat(t);
    this.t=null;
    console.log(this.added);
  }

  EditFund(f:fund){
    if (this.added!=[] && this.selected!=[]  ){
      f.tags.concat(this.selected).concat(this.added);
    }
    else if(this.added!=[]  && this.selected==[]  ){
      f.tags.concat(this.added);
    }
    else f.tags.concat(this.selected);
    
    this.FS.Updatefund(f,this.catId).subscribe(()=>this._router.navigateByUrl("/admin/Fund/showAll"));
  }

  RemoveTag(f:fund,t:string){

    const index = f.tags.indexOf(t);
    if (index > -1) {
      f.tags.splice(index, 1);
    }
    

  }
  ngOnInit(): void {

    this.added=this.f.tags;

    this.FS.FundTags().subscribe(res => {
      console.log(res);
      this.tags=res;
    });
    
    this.fId = Number(this._Activatedroute.snapshot.paramMap.get("idf"));
    console.log(this.fId);
    if (this.fId != null) {
      this.FS.findFundById(this.fId).subscribe(res => {
        console.log(res);
        this.f = res;
        

      })
      this.catId = Number(this._Activatedroute.snapshot.paramMap.get("id"));
      if (this.catId!=null){
        this.FCS.findCatById(this.catId).subscribe(res=>{
          console.log(res);
          this.c=res;
        })
      }
      console.log(this.catId);
    };
  
  }
  
  

}
