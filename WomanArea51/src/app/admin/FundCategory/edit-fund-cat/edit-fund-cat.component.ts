import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FundCat } from 'src/app/Model/FundCat';
import { ImageModel } from 'src/app/Model/ImageModel';
import { FundCatService } from 'src/app/_services/FundCategory/fund-cat.service';

@Component({
  selector: 'app-edit-fund-cat',
  templateUrl: './edit-fund-cat.component.html',
  styleUrls: ['./edit-fund-cat.component.css']
})
export class EditFundCatComponent implements OnInit {


  id:number;
  catType:string='';
  catImg:ImageModel;
  catId:number;
  image:number;
  f:FundCat;
  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  constructor(private FCS:FundCatService, private _router:Router,private _Activatedroute:ActivatedRoute, private httpClient: HttpClient) { }

  
  UpdateFundCat(f:FundCat){
    if (this.catType!=""){
      f.categoryType=this.catType;
      this.FCS.UpdateFundCat(f).subscribe(()=>this._router.navigateByUrl("/admin/FundCategory/showAll"));
    }
    if (this.image===1){
    f.categoryImg=this.retrievedImage;
     this.FCS.UpdateFundCat(f).subscribe(()=>this._router.navigateByUrl("/admin/FundCategory/showAll"));
   }
   }
   onFileChanged(event){
    //Select File
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
         return this.httpClient.get<ImageModel>('http://localhost:8081/fundCat/upload/get/' + this.imageName);
    }
  ngOnInit(): void {
    this.id=Number(this._Activatedroute.snapshot.paramMap.get("id"));
    if(this.id!=null){
      this.FCS.findCatById(this.id).subscribe(res => {
        console.log(res);
        this.f=res;
        
      });
  }
}

}
