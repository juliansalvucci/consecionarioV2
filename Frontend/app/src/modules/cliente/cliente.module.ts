import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteListaComponent } from './cliente-lista/cliente-lista.component';
import { ClienteComponent } from './cliente/cliente.component';



@NgModule({
  declarations: [
    ClienteListaComponent,
    ClienteComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ClienteModule { }
