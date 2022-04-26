import { Component, OnInit } from '@angular/core';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Mensaje } from './models/mensaje';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  mensaje:Mensaje = new Mensaje();
  private client:Client;
  mensajes :Mensaje[]=[];
  
  connectado:boolean =false;
  escribiendo:string;
  constructor() { }
  //messageList: {mensaje.texto, userName: string, mine: boolean}[] = [];
  userList: string[] = [];
  ngOnInit(): void {
    this.client= new Client();
    this.client.webSocketFactory = () =>{
      return new SockJS("http://localhost:8081/chat-websocket");
    }

    this.client.onConnect =(frames)=>{
      console.log('connect: '+this.client.connected+ ' : '+frames);
      this.connectado=true;
      (userList: string[]) => {
        this.userList = userList;
      };
      this.client.subscribe('/chat/mensaje',e=>{
        let mensaje:Mensaje =JSON.parse(e.body)as Mensaje;
        mensaje.fecha=new Date(mensaje.fecha) ;
        
       
        if(!this.mensaje.color && mensaje.tipo=="new_user"&& this.mensaje.username==mensaje.username){
          this.mensaje.color=mensaje.color;
        }

        this.mensajes.push(mensaje);
          console.log(mensaje);
      });

      this.client.subscribe('/chat/escribiendo',e=>{
        this.escribiendo =e.body;
        setTimeout(()=> this.escribiendo= '',3000)
      });
      this.mensaje.tipo="new_user";
      this.client.publish({destination: '/app/mensaje',body:JSON.stringify(this.mensaje)});

    }
    this.client.onDisconnect =(frames)=>{
      console.log('Deconnect: '+ !this.client.connected+ ' : '+frames);
      this.connectado=false;
  }}
  connecter():void{
    this.client.activate();
  }
  deconnecter():void{
    this.client.deactivate();
  }

  enviarMensaje():void{
    this.mensaje.user="sirine";
    this.mensaje.tipo="MENSAJE";
      this.client.publish({destination: '/app/mensaje',body:JSON.stringify(this.mensaje)});
    this.mensaje.texto='';
  }

  escribirEvento():void{
    this.client.publish({destination: '/app/escribiendo',body:JSON.stringify(this.mensaje.username)});

  }
}
