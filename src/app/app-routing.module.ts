import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatComponent } from './chat/chat.component';
import { ChatroomComponent } from './chatroom/chatroom.component';

const routes: Routes = [
  {path:'',redirectTo:'chat',pathMatch:'full'},
  {path:'chat',component:ChatComponent},
  {path:'chatroom/:username',component:ChatroomComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
