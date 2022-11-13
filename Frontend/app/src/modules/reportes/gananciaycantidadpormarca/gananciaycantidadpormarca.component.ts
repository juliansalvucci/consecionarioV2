import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { IGananciaYCantidadPorMarca } from 'src/interfaces/IGananaciaYCantidadPorMarca';
import { PaisService } from 'src/services/pais/pais.service';
import { ReportesService } from 'src/services/reportes/reportes.service';

@Component({
  selector: 'app-gananciaycantidadpormarca',
  templateUrl: './gananciaycantidadpormarca.component.html',
  styleUrls: ['./gananciaycantidadpormarca.component.css']
})
export class GananciaycantidadpormarcaComponent {

  displayedColumns: string[] = ['cantidadVentas','costo','marca'];
  dataSource!: MatTableDataSource<IGenerica>;

  cargando: boolean = false;
  filtro: string = '';

  listaFiltro!: IGenerica[];
  lista!: IGenerica[];

  constructor(
    private service: ReportesService,
    public _MatPaginatorIntl: MatPaginatorIntl
  ) {
    this.consultar();
  }

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngOnInit(): void {
    this._MatPaginatorIntl.itemsPerPageLabel = 'items por página';
  }

  configTable() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

 consultar() {
    try {
     this.service.consulta().subscribe((r: IGenerica[]) => {
        console.log(r);
        this.lista = r;
        this.dataSource = new MatTableDataSource(this.lista);
        this.configTable();
        this.cargando = false;
      });
    } catch (e) {
      console.log(e);
      this.cargando = false;
    }
  }

  filtrar() {
    if (this.filtro == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter((f) =>
        f.marca
          ?.toLowerCase()
          .trim()
          .includes(this.filtro.toLocaleLowerCase())
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }

}

export interface IGenerica extends IGananciaYCantidadPorMarca{}