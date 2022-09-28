import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaisComponent } from './pais/pais.component';
import { PaisListaComponent } from './pais-lista/pais-lista.component';
import { SharedModule } from 'src/shared/shared/shared.module';



@NgModule({
  declarations: [
    PaisComponent,
    PaisListaComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    PaisComponent,
    PaisListaComponent
  ]
})
export class PaisModule { }
