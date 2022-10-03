import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AutoListaComponent } from "src/modules/auto/auto-lista/auto-lista.component";
import { ClienteListaComponent } from "src/modules/cliente/cliente-lista/cliente-lista.component";
import { MarcaListaComponent } from "src/modules/marca/marca-lista/marca-lista.component";
import { ModeloListaComponent } from "src/modules/modelo/modelo-lista/modelo-lista.component";
import { PaisListaComponent } from "src/modules/pais/pais-lista/pais-lista.component";
import { LoginComponent } from "./layout/login/login.component";
import { SidebarComponent } from "./layout/sidebar/sidebar.component";


const routes: Routes = [

  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'listaPaises',
    component: PaisListaComponent,
  },
  {
    path: 'listaModelos',
    component: ModeloListaComponent,
  },
  {
    path: 'listaMarcas',
    component: MarcaListaComponent,
  },
  {
    path: 'listaAutos',
    component: AutoListaComponent,
  },
  {
    path: 'listaClientes',
    component: ClienteListaComponent,
  },
 



  { path: '**', redirectTo: '' },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }