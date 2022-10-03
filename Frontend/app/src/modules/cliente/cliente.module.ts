import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteListaComponent } from './cliente-lista/cliente-lista.component';
import { ClienteComponent } from './cliente/cliente.component';
import { SharedModule } from 'src/shared/shared/shared.module';



@NgModule({
  declarations: [
    ClienteListaComponent,
    ClienteComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    ClienteComponent,
    ClienteListaComponent
  ]
})
export class ClienteModule { }
