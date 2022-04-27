import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FundCat } from '../model/FundCat';
import { FundCatService } from '../shared/fund-cat.service';

@Component({
  selector: 'app-add-fund-cat',
  templateUrl: './add-fund-cat.component.html',
  styleUrls: ['./add-fund-cat.component.css']
})
export class AddFundCatComponent implements OnInit {
  
  FC : FundCat = new FundCat();
 
  constructor(private FCS:FundCatService, private _router:Router) { }
  
  addFundCatgeory(){
    this.FCS.addFundCat(this.FC).subscribe(()=>this._router.navigateByUrl("/ListFundCategories"));
  }

  ngOnInit(): void {
  }

}
