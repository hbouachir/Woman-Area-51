import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Mensaje } from '../chat/models/mensaje';

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css']
})
export class ChatroomComponent implements OnInit {
  mensaje:Mensaje = new Mensaje();
  private client:Client;
  mensajes :Mensaje[]=[];
  currentPost = new Mensaje();
  connectado:boolean =true;
  escribiendo:string;
 // user:string;
  constructor(private activatedRoute : ActivatedRoute) { }
  //messageList: {mensaje.texto, userName: string, mine: boolean}[] = [];
  userList: string[] = [];
  ngOnInit(): void {
  this.mensaje.user= this.activatedRoute.snapshot.params['username'];
    this.client= new Client();
    this.client.webSocketFactory = () =>{
      return new SockJS("http://localhost:8081/chat-websocket");
  
    }

    this.client.onConnect =(frames)=>{
      console.log('connect: '+this.client.connected+ ' : '+frames);
      this.connectado=true;
      
      this.client.subscribe('/chat/mensaje',e=>{
        let mensaje:Mensaje =JSON.parse(e.body)as Mensaje;
        mensaje.fecha=new Date(mensaje.fecha) ;
        
      

        this.mensajes.push(mensaje);
          console.log(mensaje);
      });

      this.client.subscribe('/chat/escribiendo',e=>{
        this.escribiendo =e.body;
        setTimeout(()=> this.escribiendo= '',3000)
      });
    //  this.mensaje.tipo="new_user";
      //this.client.publish({destination: '/app/mensaje',body:JSON.stringify(this.mensaje)});

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
    this.client.activate();
 this.mensaje.username="sirine";
// this.mensaje.username=this.client.activate();
    this.mensaje.tipo="MENSAJE";
    this.client.publish({destination: '/app/mensaje',body:JSON.stringify(this.mensaje)});
    this.mensaje.texto='';
  }

  escribirEvento():void{
    this.client.publish({destination: '/app/escribiendo',body:JSON.stringify(this.mensaje.username)});

  }
}
