import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './admin/login/login.component';
import { IndexComponent } from './admin/index/index.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { AddFundCatComponent } from './admin/FundCategory/add-fund-cat/add-fund-cat.component';
import { EditFundCatComponent } from './admin/FundCategory/edit-fund-cat/edit-fund-cat.component';
import { ShowAllFundCatComponent } from './admin/FundCategory/show-all-fund-cat/show-all-fund-cat.component';
import { AddFundComponent } from './admin/Fund/add-fund/add-fund.component';
import { ShowAllFundsComponent } from './admin/Fund/show-all-funds/show-all-funds.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { FooterComponent } from './admin/layout/footer/footer/footer.component';
import { NavbarComponent } from './admin/layout/navbar/navbar/navbar.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { SelectCategoryComponent } from './admin/Fund/add-fund/select-category/select-category.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { EditFundComponent } from './admin/Fund/edit-fund/edit-fund/edit-fund.component';
import { UpdateSelectedCatComponent } from './admin/Fund/edit-fund/edit-fund/update-cat/update-selected-cat/update-selected-cat.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    IndexComponent,
    AddFundCatComponent,
    EditFundCatComponent,
    ShowAllFundCatComponent,
    AddFundComponent,
    ShowAllFundsComponent,
    FooterComponent,
    NavbarComponent,
    SelectCategoryComponent,
    EditFundComponent,
    UpdateSelectedCatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    NgSelectModule 
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
