import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SkeletonComponent } from "src/app/layout/skeleton/skeleton.component";
import { PaisListaComponent } from "./pais-lista/pais-lista.component";


const routes: Routes = [
    { 
      path: '',
      component: SkeletonComponent,
      children: [
  
        {
          path:'/listaPaises',
          component: PaisListaComponent
        },
        
      ]},
    { path: '**', redirectTo: '/' },
  ];
  
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })

  export class PaisRoutingModule { }

