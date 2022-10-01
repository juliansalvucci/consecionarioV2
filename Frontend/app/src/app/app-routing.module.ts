import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AutoListaComponent } from "src/modules/auto/auto-lista/auto-lista.component";
import { MarcaListaComponent } from "src/modules/marca/marca-lista/marca-lista.component";
import { ModeloListaComponent } from "src/modules/modelo/modelo-lista/modelo-lista.component";
import { PaisListaComponent } from "src/modules/pais/pais-lista/pais-lista.component";


const routes: Routes = [
    {
      path: 'listaPaises',
      component: PaisListaComponent
    },
    {
      path: 'listaModelos',
      component: ModeloListaComponent
    },
    {
      path: 'listaMarcas',
      component: MarcaListaComponent
    },
    {
      path: 'listaAutos',
      component: AutoListaComponent
    },
    
    
    
    { path: "**", redirectTo: '' }
  ];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }