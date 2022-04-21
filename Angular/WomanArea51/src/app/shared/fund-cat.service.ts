import { HttpClient, } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FundCat } from '../model/FundCat';

@Injectable({
  providedIn: 'root'
})
export class FundCatService {

  FundCatsUrl = "http://127.0.0.1:8081/find-all-fund-categorys"
  constructor(private _http: HttpClient) { }

  getFundCats(): Observable<FundCat[]> {
    return this._http.get<FundCat[]>(this.FundCatsUrl);

  }

  getAllFundCatByType(categoryType: string): Observable<FundCat[]> {
    return this._http.get<FundCat[]>("http://127.0.0.1:8081/find-fund-categories-by-Type/" + categoryType);
  }


  deleteFundCateById(id: number) {
    return this._http.delete("http://127.0.0.1:8081/remove-fund-category/" + id);
  }

  addFundCat(FC: FundCat) {
    return this._http.post<FundCat>("http://localhost:8081/add-fund-category", FC);
  }

  findCatById(id:number){
    return this._http.get<FundCat>("http://127.0.0.1:8081/find-fund-category/" + id);
  }
  UpdateFundCat(FC :FundCat){
    return this._http.put<FundCat>("http://localhost:8081/fund-category/Update", FC);
  }
}
