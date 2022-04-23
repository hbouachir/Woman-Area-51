import { Component, OnInit } from '@angular/core';
import {HeaderComponent} from "@coreui/angular";
import {TokenStorageService} from "../../../_services/token-storage.service";
import {AuthService} from "../../../_services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-default-header-u',
  templateUrl: './default-header-u.component.html',
  styleUrls: ['./default-header-u.component.scss']
})
export class DefaultHeaderUComponent extends HeaderComponent{
  isLoggedIn=false;
  roles: string[] = [];
  username="" ;
  constructor(private tokenStorage: TokenStorageService,private router : Router) {
    super();
  }
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      this.username=this.tokenStorage.getUser().username;
      this.router.navigate(['/']);
    }
  }

}
