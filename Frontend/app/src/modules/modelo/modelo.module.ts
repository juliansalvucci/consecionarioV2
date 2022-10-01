import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModeloComponent } from './modelo/modelo.component';
import { ModeloListaComponent } from './modelo-lista/modelo-lista.component';
import { SharedModule } from 'src/shared/shared/shared.module';



@NgModule({
  declarations: [
    ModeloComponent,
    ModeloListaComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    ModeloComponent,
    ModeloListaComponent
  ]
})
export class ModeloModule { }
