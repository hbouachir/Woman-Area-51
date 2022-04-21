import { Component, OnInit } from '@angular/core';
import { FundCat } from 'src/app/Model/FundCat';
import { FundCatService } from 'src/app/_services/FundCategory/fund-cat.service';

@Component({
  selector: 'app-select-category',
  templateUrl: './select-category.component.html',
  styleUrls: ['./select-category.component.css']
})
export class SelectCategoryComponent implements OnInit {

  keyword:string;
  listFundCat:FundCat[];
  constructor(private FCS:FundCatService) { }

  ngOnInit(): void {
    this.FCS.getFundCats().subscribe(res => {
      console.log(res);
      
      this.listFundCat = res
    });
    
  }

}
