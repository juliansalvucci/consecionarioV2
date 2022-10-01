import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { SharedModule } from "src/shared/shared/shared.module";
import { AutoListaComponent } from "./auto-lista/auto-lista.component";
import { AutoComponent } from "./auto/auto.component";




@NgModule({
  declarations: [
    AutoComponent,
    AutoListaComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
  ],
  exports: [
    AutoComponent,
    AutoListaComponent
  ]
})
export class AutoModule { }
