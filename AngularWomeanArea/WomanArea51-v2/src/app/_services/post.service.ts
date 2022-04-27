import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import{Post}from'src/app/models/post';
import { Filepost } from '../models/filepost';
import { AuthService } from './auth.service';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'mon-jeton'
  })}
@Injectable({
  providedIn: 'root'
})
export class PostService {

 
  posts!: Post[];
  //post : Post;
  apiURL : string ='http://localhost:8081/listetousposts';
  apiURLL : string ='http://localhost:8081/addpost';
  apiURLD : string='http://localhost:8081/post';
  apiURLup : string='http://localhost:8081/postupp';
  apiURLLL : string ='http://localhost:8081/getpost';
  apiURLIMAGE : string ='http://localhost:8081/image';
  apiURLIMAGEE : string ='http://localhost:8081/imagee';
  

  
    constructor( private http : HttpClient,private authService : AuthService) { 
   }

    listePosts():Observable<Post[]>{
    
      return this.http.get<Post[]>(this.apiURL,httpOptions);
    }
    ajouterPost(postt:Post):Observable<Post>{
    
      return this.http.post<Post>(this.apiURLL, postt,httpOptions);
       }
}
