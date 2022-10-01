import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

   public STR_PAIS = 'listaPaises'
   public STR_MODELO = 'listaModelos'
   public STR_MARCA = 'listaMarcas'

}
