import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { IAuto } from 'src/interfaces/IAuto';
import { VentaComponent } from 'src/modules/venta/venta/venta.component';
import { AutoService } from 'src/services/auto/auto.service';
import { DataService } from 'src/services/data.service';
import { AutoComponent } from '../auto/auto.component';

@Component({
  selector: 'app-auto-lista',
  templateUrl: './auto-lista.component.html',
  styleUrls: ['./auto-lista.component.css'],
})
export class AutoListaComponent implements OnInit {
  displayedColumns: string[] = [
    'precio',
    'costo',
    'modelo',
    'marca',
    'pais',
    'acciones',
  ];
  dataSource!: MatTableDataSource<IGenerica>;

  cargando: boolean = false;
  filtro: string = '';

  listaFiltro!: IGenerica[];
  lista!: IGenerica[];

  constructor(
    public dialog: MatDialog,
    private service: AutoService,
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
    this.service.consulta().subscribe((r: IGenerica[]) => {
      console.log(r);
      this.lista = r.filter((f) => f.vendido == false);
      this.dataSource = new MatTableDataSource(this.lista);
      this.configTable();
      this.cargando = false;
    });
  }

  abrirModal(id: number): void {
    this.dataService.id = id;
    const dialogRef = this.dialog.open(AutoComponent, {
      width: '450px',
    });
    dialogRef.afterClosed().subscribe(() => {
      if (this.dataService.object != null) {
        this.lista = this.lista.filter((element) => element.id != id);
        this.lista.push(this.dataService.object);
        this.dataSource = new MatTableDataSource(this.lista);
        this.configTable();
        this.dataService.object = null;
      }

      this.cargando = false;
    });
  }

  abrirModal2(id: number): void {
    this.dataService.id = id;
    const dialogRef = this.dialog.open(VentaComponent, {
      width: '750px',
    });
    dialogRef.afterClosed().subscribe(() => {
      if (this.dataService.object != null) {
        this.lista = this.lista.filter((element) => element.id != id);
        this.dataSource = new MatTableDataSource(this.lista);
        this.configTable();
        this.dataService.object = null;
      }

      this.cargando = false;
    });
  }

  eliminar(id: number) {
    window.confirm("¿SEGURO DESEA ELIMINAR?")
    this.service.baja(id).subscribe((r) => {
      if (r) {
        //Si el back me devuelve un true.
        this.lista = this.lista.filter((element) => element.id != id);
        this.dataSource = new MatTableDataSource(this.lista);
        this.configTable();
      }
      this.cargando = false;
    });
  }

  filtrar() {
    if (this.filtro == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter(
        (f) =>
          f.modelo.marca.nombreMarca
            ?.toLowerCase()
            .trim()
            .includes(this.filtro.toLocaleLowerCase()) ||
          f.modelo.nombreModelo
            ?.toLowerCase()
            .trim()
            .includes(this.filtro.toLocaleLowerCase())
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }
}

export interface IGenerica extends IAuto{}