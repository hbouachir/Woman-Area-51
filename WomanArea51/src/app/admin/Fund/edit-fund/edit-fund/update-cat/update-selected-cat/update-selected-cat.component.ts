import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { fund } from 'src/app/Model/fund';
import { FundCat } from 'src/app/Model/FundCat';
import { FundService } from 'src/app/_services/Fund/fund.service';
import { FundCatService } from 'src/app/_services/FundCategory/fund-cat.service';

@Component({
  selector: 'app-update-selected-cat',
  templateUrl: './update-selected-cat.component.html',
  styleUrls: ['./update-selected-cat.component.css']
})
export class UpdateSelectedCatComponent implements OnInit {

  keyword: string;
  listFundCat: FundCat[];
  catId: number;
  fId: number;
  id: number;
  f: fund;
  cat: FundCat;
  constructor(private FCS: FundCatService, private FS: FundService, private _router: Router, private _Activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.fId = Number(this._Activatedroute.snapshot.paramMap.get("idf"));
    console.log(this.fId);
    if (this.fId != null) {
      this.FS.findFundById(this.fId).subscribe(res => {
        console.log(res);
        this.f = res;
        this.FS.GetFundCat(this.fId).subscribe(res => {
          this.cat = res;
          this.catId = res.categoryId;
        }
        );

      })

      console.log(this.catId);
    };

    this.FCS.getFundCats().subscribe(res => {
      console.log(res);

      this.listFundCat = res
    });
  }
}
