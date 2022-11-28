import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RangoComponent } from './rango/rango.component';
import { RangolistaComponent } from './rangolista/rangolista.component';
import { SharedModule } from 'src/shared/shared/shared.module';



@NgModule({
  declarations: [
    RangoComponent,
    RangolistaComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ]
})
export class RangoModule { }
