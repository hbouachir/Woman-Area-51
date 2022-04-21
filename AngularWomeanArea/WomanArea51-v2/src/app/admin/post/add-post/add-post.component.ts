import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/_services/post.service';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.scss']
})
export class AddPostComponent implements OnInit {

  newPost= new Post();
  constructor( private router :Router,private postService: PostService) { }
 /* addPost (){
   // console.log(this.newPost)
 //  this.postService.ajouterPost(this.newPost);
  }*/
  addPost(){
  this.postService.ajouterPost(this.newPost).subscribe(postt=>{
    console.log(postt);
  })  ;
  this.router.navigate(['post']).then(()=>{window.location.reload()});
  }
  ngOnInit(): void {
   
  }

}
