import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { fund } from 'src/app/Model/fund';
import { FundCat } from 'src/app/Model/FundCat';

@Injectable({
  providedIn: 'root'
})
export class FundService {
  
  ID:string;
  fundsUrl = "http://127.0.0.1:8081/find-all-funds"
  constructor(private _http: HttpClient) { }

  getfunds(): Observable<fund[]> {
    return this._http.get<fund[]>(this.fundsUrl);
  }

  getAllfundsByType(categoryType: string): Observable<fund[]> {
    return this._http.get<fund[]>("http://127.0.0.1:8081/find-funds-by-category/" + categoryType);
  }

  deletefundeById(id: number) {
    return this._http.delete("http://127.0.0.1:8081/remove-fund/" + id);
  }

  addfund(F: fund, id:number){
    return this._http.post<fund>("http://localhost:8081/propose-fund/"+id,F);
  }

  findFundById(id:number){
    return this._http.get<fund>("http://127.0.0.1:8081/find-fund/"+ id);
  }

  Updatefund(FC :fund,id:number){
    return this._http.put<fund>("http://localhost:8081/fund/Update/"+id ,FC);
  }

  FundTags(){
    return this._http.get<string[]>("http://localhost:8081/Fund-tags-list");
  }

  GetFundCat(id:number){
    return this._http.get<FundCat>("http://localhost:8081/get-fundCategory/"+id);
  }
}
