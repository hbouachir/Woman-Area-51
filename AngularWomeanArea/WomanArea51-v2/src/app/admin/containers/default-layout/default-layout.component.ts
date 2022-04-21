import { Component } from '@angular/core';

import { navItems } from './_nav';
import {AuthService} from "../../../_services/auth.service";
import {TokenStorageService} from "../../../_services/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
})
export class DefaultLayoutComponent {

  public navItems = navItems;

  public perfectScrollbarConfig = {
    suppressScrollX: true,
  }

  isLoggedIn = false;
  roles: string[] = [];

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,private router : Router) {}

  ngOnInit(): void{
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
    if(!(this.isLoggedIn)) this.router.navigate(['/admin/login']);

  }
}
