import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GananciaycantidadpormarcaComponent } from './gananciaycantidadpormarca/gananciaycantidadpormarca.component';
import { SharedModule } from 'src/shared/shared/shared.module';



@NgModule({
  declarations: [
    GananciaycantidadpormarcaComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    GananciaycantidadpormarcaComponent
  ]
})
export class ReportesModule { }
