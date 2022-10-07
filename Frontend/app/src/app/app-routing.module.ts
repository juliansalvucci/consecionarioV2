import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AutoListaComponent } from "src/modules/auto/auto-lista/auto-lista.component";
import { CategoriaListaComponent } from "src/modules/categoria/categoria-lista/categoria-lista.component";
import { ClienteListaComponent } from "src/modules/cliente/cliente-lista/cliente-lista.component";
import { MarcaListaComponent } from "src/modules/marca/marca-lista/marca-lista.component";
import { ModeloListaComponent } from "src/modules/modelo/modelo-lista/modelo-lista.component";
import { PaisListaComponent } from "src/modules/pais/pais-lista/pais-lista.component";
import { LoginComponent } from "./layout/login/login.component";
import { SkeletonComponent } from "./layout/skeleton/skeleton.component";


const routes: Routes = [

  {
    path: '',
    component: LoginComponent
  },

  {
    path: 'system', 
    component: SkeletonComponent,
    children : [
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
      {
        path: 'listaCategorias',
        component: CategoriaListaComponent,
      },

    ]
  },


  
 
  



  { path: '**', redirectTo: '' },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }