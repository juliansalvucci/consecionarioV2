import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import * as moment from 'moment';
import { IGananciaYCantidadPorModeloYMarca } from 'src/interfaces/IGananciaPorModeloYMarca';
import { ReportesService } from 'src/services/reportes/reportes.service';

@Component({
  selector: 'app-gananciapormodeloymarca',
  templateUrl: './gananciapormodeloymarca.component.html',
  styleUrls: ['./gananciapormodeloymarca.component.css'],
})
export class GananciapormodeloymarcaComponent implements OnInit {
  displayedColumns: string[] = ['cantidadVentas', 'costo', 'marca', 'modelo'];
  dataSource!: MatTableDataSource<IGenerica>;

  cargando: boolean = false;
  filtro: string = '';

  listaFiltro!: IGenerica[];
  lista!: IGenerica[];

  registerForm = this.fb.group({
    fechaDesde: [''],
    fechaHasta: [''],
  });

  constructor(
    private service: ReportesService,
    public _MatPaginatorIntl: MatPaginatorIntl,
    private fb: FormBuilder
  ) {
    this.dataSource = new MatTableDataSource(this.lista);
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

  buscar() {
    let fechaDesde = moment(this.registerForm.value.fechaDesde)
      .tz('America/Argentina/Cordoba')
      .format();
    let fechaHasta = moment(this.registerForm.value.fechaHasta)
      .tz('America/Argentina/Cordoba')
      .format();

    this.service
      .consulta3(fechaDesde, fechaHasta)
      .subscribe((r: IGenerica[]) => {
        console.log(r);
        this.lista = r;
        this.dataSource = new MatTableDataSource(this.lista);
        this.configTable();
        this.cargando = false;
      });
  }

  filtrar() {
    if (this.filtro == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter((f) =>
        f.marca?.toLowerCase().trim().includes(this.filtro.toLocaleLowerCase())
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }
}

export interface IGenerica extends IGananciaYCantidadPorModeloYMarca{}
