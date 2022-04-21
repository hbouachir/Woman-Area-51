import {NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LayoutFrontComponent } from './layout-front/layout-front.component';
import { FooterFrontComponent } from './footer-front/footer-front.component';
import { AboutComponent } from './about/about.component';
import { FundCatComponent } from './fund-cat/fund-cat.component';
import { HttpClientModule } from '@angular/common/http';
import { AddFundCatComponent } from './add-fund-cat/add-fund-cat.component';
import { HomeComponent } from './home/home.component';



@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LayoutFrontComponent,
    FooterFrontComponent,
    AboutComponent,
    FundCatComponent,
    AddFundCatComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
