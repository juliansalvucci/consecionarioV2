import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GananciaycantidadpormarcaComponent } from './gananciaycantidadpormarca/gananciaycantidadpormarca.component';
import { SharedModule } from 'src/shared/shared/shared.module';
import { GananciaporempleadoComponent } from './gananciaporempleado/gananciaporempleado.component';
import { GananciapormodeloymarcaComponent } from './gananciapormodeloymarca/gananciapormodeloymarca.component';
import { VentasPorCategoriasComponent } from './ventas-por-categorias/ventas-por-categorias.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { DetallePorVendedorComponent } from './detalle-por-vendedor/detalle-por-vendedor.component';



@NgModule({
  declarations: [
    GananciaycantidadpormarcaComponent,
    GananciaporempleadoComponent,
    GananciapormodeloymarcaComponent,
    VentasPorCategoriasComponent,
    DetallePorVendedorComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    NgxChartsModule,
  ],
  exports: [
    GananciaycantidadpormarcaComponent
  ]
})
export class ReportesModule { }
