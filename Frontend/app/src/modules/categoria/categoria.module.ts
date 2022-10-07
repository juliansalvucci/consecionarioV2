import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriaListaComponent } from './categoria-lista/categoria-lista.component';
import { CategoriaComponent } from './categoria/categoria.component';
import { SharedModule } from 'src/shared/shared/shared.module';



@NgModule({
  declarations: [
    CategoriaListaComponent,
    CategoriaComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    CategoriaListaComponent,
    CategoriaComponent
  ]
})
export class CategoriaModule { }
