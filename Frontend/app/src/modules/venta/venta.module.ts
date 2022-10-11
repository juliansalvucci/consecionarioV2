import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VentaComponent } from './venta/venta.component';
import { VentaListaComponent } from './venta-lista/venta-lista.component';
import { SharedModule } from 'src/shared/shared/shared.module';



@NgModule({
  declarations: [
    VentaComponent,
    VentaListaComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ]
})
export class VentaModule { }
