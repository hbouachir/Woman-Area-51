import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddFundComponent } from './admin/Fund/add-fund/add-fund.component';
import { SelectCategoryComponent } from './admin/Fund/add-fund/select-category/select-category.component';
import { EditFundComponent } from './admin/Fund/edit-fund/edit-fund/edit-fund.component';
import { UpdateSelectedCatComponent } from './admin/Fund/edit-fund/edit-fund/update-cat/update-selected-cat/update-selected-cat.component';
import { ShowAllFundsComponent } from './admin/Fund/show-all-funds/show-all-funds.component';
import { AddFundCatComponent } from './admin/FundCategory/add-fund-cat/add-fund-cat.component';
import { EditFundCatComponent } from './admin/FundCategory/edit-fund-cat/edit-fund-cat.component';
import { ShowAllFundCatComponent } from './admin/FundCategory/show-all-fund-cat/show-all-fund-cat.component';
import { NavbarComponent } from './admin/layout/navbar/navbar/navbar.component';
import {LoginComponent} from "./admin/login/login.component";

const routes: Routes = [
{ path: 'admin/login',component: LoginComponent},
{ path: 'navbar',component: NavbarComponent},
{ path: 'admin/FundCategory/add',component: AddFundCatComponent},
{ path: 'admin/FundCategory/update/:id',component: EditFundCatComponent},
{ path: 'admin/FundCategory/showAll',component: ShowAllFundCatComponent},
{ path: 'admin/Fund/showAll',component: ShowAllFundsComponent},
{ path: 'admin/Fund/add/select-category/confirm/:id',component: AddFundComponent},
{ path: 'admin/Fund/add/select-category',component: SelectCategoryComponent},
{ path: 'admin/Fund/edit/update-category/:idf/confirm/:id',component: EditFundComponent},
{ path: 'admin/Fund/edit/update-category/:idf',component: UpdateSelectedCatComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
