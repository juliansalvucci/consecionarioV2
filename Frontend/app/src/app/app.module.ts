import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PaisModule } from 'src/modules/pais/pais.module';
import { HttpClientModule } from '@angular/common/http';
import { MarcaModule } from 'src/modules/marca/marca.module';
import { ModeloModule } from 'src/modules/modelo/modelo.module';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { SharedModule } from 'src/shared/shared/shared.module';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { SkeletonComponent } from './layout/skeleton/skeleton.component';
import { AppRoutingModule } from './app-routing.module';
import { AutoModule } from 'src/modules/auto/auto.module';
import { ClienteModule } from 'src/modules/cliente/cliente.module';
import { LoginComponent } from './layout/login/login.component';
import { FooterComponent } from './layout/footer/footer.component';
import { CategoriaModule } from 'src/modules/categoria/categoria.module';
import { VentaModule } from 'src/modules/venta/venta.module';
import { ReportesModule } from 'src/modules/reportes/reportes.module';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { RangoModule } from 'src/modules/rango/rango.module';


@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    SkeletonComponent,
    LoginComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    PaisModule,
    MarcaModule,
    ModeloModule,
    AutoModule,
    ClienteModule,
    CategoriaModule,
    RangoModule,
    VentaModule,
    ReportesModule,
    HttpClientModule,
    SharedModule,
    NgxChartsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
