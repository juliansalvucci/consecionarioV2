import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MarcaListaComponent } from './marca-lista/marca-lista.component';
import { MarcaComponent } from './marca/marca.component';
import { SharedModule } from 'src/shared/shared/shared.module';



@NgModule({
  declarations: [
    MarcaListaComponent,
    MarcaComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    MarcaListaComponent,
    MarcaComponent
  ]
})
export class MarcaModule { }
