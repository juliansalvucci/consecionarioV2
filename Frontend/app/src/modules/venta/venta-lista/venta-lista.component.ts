import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { IVenta } from 'src/interfaces/IVenta';
import { DataService } from 'src/services/data.service';
import { VentaService } from 'src/services/venta/venta.service';
import { VentaComponent } from '../venta/venta.component';

@Component({
  selector: 'app-venta-lista',
  templateUrl: './venta-lista.component.html',
  styleUrls: ['./venta-lista.component.css']
})
export class VentaListaComponent implements OnInit {

  displayedColumns: string[] = ['fecha','modelo','marca','cliente'];
  dataSource!: MatTableDataSource<IGenerica>;

  cargando: boolean = false;
  filtro: string = '';

  listaFiltro!: IGenerica[];
  lista!: IGenerica[];

  constructor(
    public dialog: MatDialog,
    private service: VentaService,
    private dataService: DataService,
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

  consultar(): void {
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

  abrirModal(): void {
    const dialogRef = this.dialog.open(VentaComponent, {
      width: '450px',
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (this.dataService.object != null) {
        this.lista.push(this.dataService.object);
        this.dataSource = new MatTableDataSource(this.lista);
        this.configTable();
        this.dataService.object = null;
      }
      this.cargando = false;
    });
  }

  
  filtrar() {
    if (this.filtro == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter((f) =>
        f.cliente.nombreCliente
          ?.toLowerCase()
          .trim()
          .includes(this.filtro.toLocaleLowerCase())
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }
  

}

export interface IGenerica extends IVenta{}
