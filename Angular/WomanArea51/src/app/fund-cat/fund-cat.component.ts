import { Component, OnInit } from '@angular/core';
import { FundCat } from '../model/FundCat';
import { FundCatService } from '../shared/fund-cat.service';

@Component({
  selector: 'app-fund-cat',
  templateUrl: './fund-cat.component.html',
  styleUrls: ['./fund-cat.component.css']
})
export class FundCatComponent implements OnInit {

  listFundCat: FundCat[];
  listCategories: FundCat[];
  listCatById:FundCat[];
  type: string;
  catType:string='';
  catImg:string='';
  catId:number;
  search:boolean=true;
  fc :FundCat=new FundCat();
  constructor(private _service: FundCatService) { }

  deleteFundCateById(id: number) {
    this._service.deleteFundCateById(id).subscribe(() => this._service.getAllFundCatByType('type2')
      .subscribe(res => { this.listCategories = res }));
    this._service.getFundCats().subscribe((() => this._service.getFundCats()
      .subscribe(res => { this.listFundCat = res })));
  }


  SearchCatByType() {
    this._service.getAllFundCatByType(this.type).subscribe(res => {
      console.log(res);
      this.listCategories = res;
    });
  }


  SearchCatById(catId :number){
    if(this.catId!=null){
      this._service.findCatById(this.catId).subscribe(res => {
        console.log(res);
        this.fc=res;
        this.search=false;
      });
    }
    
  }
  UpdateFundCat(f:FundCat){
    if (this.catType!="" && /^[A-Za-z\s]*$/.test(this.catType)){
      f.categoryType=this.catType;
      this._service.UpdateFundCat(f).subscribe((() => this._service.getFundCats()
    .subscribe(res => { this.listFundCat = res })));
    }
   if (this.catImg!="" && /^[A-Za-z\s]*$/.test(this.catImg)){
     f.categoryImg=this.catImg;
     this._service.UpdateFundCat(f).subscribe((() => this._service.getFundCats()
    .subscribe(res => { this.listFundCat = res })));
   }
   
  }

  ngOnInit(): void {



    this._service.getFundCats().subscribe(res => {
      console.log(res);

      this.listFundCat = res
    });





  }


}
