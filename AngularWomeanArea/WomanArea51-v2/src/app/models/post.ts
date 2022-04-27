import { Filepost } from "src/app/models/filepost";

export class Post{
    id!: number;
    title!: string;
    body!: string;
    image!: string;
    
    createdate!: Date;
    score!: number;
    countCom!: number;
    tags!: string[];
    userp!: number;
    filespost!: Filepost[];
}