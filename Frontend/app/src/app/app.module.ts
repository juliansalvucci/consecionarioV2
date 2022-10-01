import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PaisModule } from 'src/modules/pais/pais.module';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MarcaModule } from 'src/modules/marca/marca.module';
import { ModeloModule } from 'src/modules/modelo/modelo.module';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { SharedModule } from 'src/shared/shared/shared.module';
import { NavbarComponent } from './layout/navbar/navbar.component';


@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    PaisModule,
    MarcaModule,
    ModeloModule,
    HttpClientModule,
    SharedModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
