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
   public STR_AUTO = 'listaAutos'
   public STR_CLIENTE = 'listaClientes'
   public STR_CATEGORIAS = 'listaCategorias'
   public STR_VENTAS = 'listaVentas'
   public STR_GANANCIA_MARCAS = 'gananciaPorMarca'
   public STR_GANANCIA_EMPLEADOS = 'gananciaPorEmpleado'
   public STR_GANANCIA_MODELOYMARCA = 'gananciaPorMarcaYModelo'
   public STR_GANANCIA_CATEGORIA = 'gananciaPorCategoria'
   public STR_DETALLE_POR_VENDEDOR ='detalleVentasPorVendedor'

}
