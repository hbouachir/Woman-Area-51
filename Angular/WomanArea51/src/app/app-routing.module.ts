import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { AddFundCatComponent } from './add-fund-cat/add-fund-cat.component';
import { FundCatComponent } from './fund-cat/fund-cat.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: 'about', component: AboutComponent },


  { path: "addFundCategory", component: AddFundCatComponent },
  { path: "ListFundCategories", component: FundCatComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
