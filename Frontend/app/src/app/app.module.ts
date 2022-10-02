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


@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    SkeletonComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    PaisModule,
    MarcaModule,
    ModeloModule,
    AutoModule,
    ClienteModule,
    HttpClientModule,
    SharedModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
