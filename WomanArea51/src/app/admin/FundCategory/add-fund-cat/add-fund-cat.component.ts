import { HttpClient } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FundCat } from 'src/app/Model/FundCat';
import { ImageModel } from 'src/app/Model/ImageModel';
import { FundCatService } from 'src/app/_services/FundCategory/fund-cat.service';

@Component({
  selector: 'app-add-fund-cat',
  templateUrl: './add-fund-cat.component.html',
  styleUrls: ['./add-fund-cat.component.css']
})
export class AddFundCatComponent implements OnInit {
 
  FC : FundCat=new FundCat();
  image:number; 
  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  localUrl: any[];
  constructor(private FCS:FundCatService, private _router:Router,private httpClient: HttpClient) { }

  

  addFundCatgeory(){
    if (this.image===1){
      this.FC.categoryImg=this.retrievedImage;
    }
    this.FCS.addFundCat(this.FC).subscribe(()=>this._router.navigateByUrl("/admin/FundCategory/showAll"));
  }

  
  onFileChanged(event){
    
    this.selectedFile = event.target.files[0];
    this.image=1;
  
  }
  onUpload() {
    console.log(this.selectedFile);
    
    
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
  
    
    this.httpClient.post<ImageModel>('http://localhost:8081/fundCat/upload', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );
    }
    //*Gets called when the user clicks on retieve image button to get the image from back end
    getImage() {
      
      this.httpClient.get<ImageModel>('http://localhost:8081/fundCat/upload/get/' + this.imageName)
        .subscribe(
          res => {
            this.retrieveResonse = res;
            this.base64Data = this.retrieveResonse.picByte;
            this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
          }
        );
    }
    
  ngOnInit(): void {

  }

}
