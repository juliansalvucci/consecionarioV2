import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class DataService 
{
  id: number = 0;
  idMarca: number = 0;
  documento!: string;
  object!: any;
}
