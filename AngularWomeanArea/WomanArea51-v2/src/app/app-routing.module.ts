import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DefaultLayoutComponent } from './admin/containers';
import { Page404Component } from './admin/views/pages/page404/page404.component';
import { Page500Component } from './admin/views/pages/page500/page500.component';
import { LoginComponent } from './admin/views/pages/login/login.component';
import { RegisterComponent } from './admin/views/pages/register/register.component';
import {DefaultLayoutUComponent} from "./user/default-layout-u/default-layout-u.component";
import {CommingSoonComponent} from "./user/comming-soon/comming-soon.component";
import {UserIndexComponent} from "./user/user-index/user-index.component";
import {SigninComponent} from "./user/signin/signin.component";
import {PaginationsComponent} from "./admin/views/base/paginations/paginations.component";
import { PostComponent } from './admin/post/post.component';
import { AddPostComponent } from './admin/post/add-post/add-post.component';

const routes: Routes = [

  {
    path: '',
    component: DefaultLayoutUComponent,
    children:[

     /* {
        path : '',
        component : CommingSoonComponent
      },*/
      {
        path : 'signin',
        component : SigninComponent
      },

      {
        path : '',
        component : UserIndexComponent
      }
    ]
  },
  {
    path: 'admin',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'post/show',
        component: PostComponent
      },
      {path:'post/addpost', 
      component: AddPostComponent},
      {
        path: '',
        loadChildren: () =>
          import('./admin/views/dashboard/dashboard.module').then((m) => m.DashboardModule)
      },
      {
        path: 'theme',
        loadChildren: () =>
          import('./admin/views/theme/theme.module').then((m) => m.ThemeModule)
      },
      {
        path: 'base',
        loadChildren: () =>
          import('./admin/views/base/base.module').then((m) => m.BaseModule)
      },
      {
        path: 'buttons',
        loadChildren: () =>
          import('./admin/views/buttons/buttons.module').then((m) => m.ButtonsModule)
      },
      {
        path: 'forms',
        loadChildren: () =>
          import('./admin/views/forms/forms.module').then((m) => m.CoreUIFormsModule)
      },
      {
        path: 'charts',
        loadChildren: () =>
          import('./admin/views/charts/charts.module').then((m) => m.ChartsModule)
      },
      {
        path: 'icons',
        loadChildren: () =>
          import('./admin/views/icons/icons.module').then((m) => m.IconsModule)
      },
      {
        path: 'notifications',
        loadChildren: () =>
          import('./admin/views/notifications/notifications.module').then((m) => m.NotificationsModule)
      },
      {
        path: 'widgets',
        loadChildren: () =>
          import('./admin/views/widgets/widgets.module').then((m) => m.WidgetsModule)
      },
      {
        path: 'pages',
        loadChildren: () =>
          import('./admin/views/pages/pages.module').then((m) => m.PagesModule)
      },
    ]
  },
  {
    path: '404',
    component: Page404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: Page500Component,
    data: {
      title: 'Page 500'
    }
  },
  {
    path: 'admin/login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  {path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      useHash: false,
      scrollPositionRestoration: 'top',
      anchorScrolling: 'enabled',
      initialNavigation: 'enabledBlocking',

      // relativeLinkResolution: 'legacy'
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
