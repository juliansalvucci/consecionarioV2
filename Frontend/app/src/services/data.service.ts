import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class DataService 
{
  id!: number;
  documento!: string
  object!: any
}
