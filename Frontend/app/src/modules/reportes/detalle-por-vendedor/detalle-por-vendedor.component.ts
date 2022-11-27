import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import * as moment from 'moment';
import { IDetalleVentasPorEmpleado } from 'src/interfaces/IDetalleVentasPorEmpleado';
import { ReportesService } from 'src/services/reportes/reportes.service';

@Component({
  selector: 'app-detalle-por-vendedor',
  templateUrl: './detalle-por-vendedor.component.html',
  styleUrls: ['./detalle-por-vendedor.component.css']
})
export class DetallePorVendedorComponent implements OnInit {

  displayedColumns: string[] = ['cantidadVentas', 'ganancia','modelo','marca','categoria','cliente','vendedor'];
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
    //Inicio datasource vació para que me indique el span que no hay registros inicialmente.
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
      .consulta5(fechaDesde, fechaHasta)
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
        f.nombreVendedor
          ?.toLowerCase()
          .trim()
          .includes(this.filtro.toLocaleLowerCase())
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }

}

export interface IGenerica extends IDetalleVentasPorEmpleado{}