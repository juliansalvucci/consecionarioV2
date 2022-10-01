import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { IAuto } from 'src/interfaces/IAuto';
import { IMarca } from 'src/interfaces/IMarca';
import { IModelo } from 'src/interfaces/IModelo';
import { AutoService } from 'src/services/auto/auto.service';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { ModeloService } from 'src/services/modelo/modelo.service';
import { AutoComponent } from '../auto/auto.component';

@Component({
  selector: 'app-auto-lista',
  templateUrl: './auto-lista.component.html',
  styleUrls: ['./auto-lista.component.css']
})
export class AutoListaComponent implements OnInit {

  displayedColumns: string[] = ['nombre', 'acciones'];
  dataSource!: MatTableDataSource<IGenerica>;

  cargando: boolean = false;
  filtro: string = '';

  listaFiltro!: IGenerica[];
  lista!: IGenerica[];

  constructor(
    public dialog: MatDialog,
    private service: AutoService,
    private service1: MarcaService,
    private service2: ModeloService,
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

  abrirModal(id: number): void {
    this.dataService.id = id;
    const dialogRef = this.dialog.open(AutoComponent, {
      width: '450px',
    });
    dialogRef.afterClosed().subscribe((result) => {
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

  eliminar(id: number) {
    try {
      this.service.baja(id).subscribe((r) => {
        if (r) {
          //Si el back me devuelve un true.
          this.lista = this.lista.filter((element) => element.id != id);
          this.dataSource = new MatTableDataSource(this.lista);
          this.configTable();
        }
        this.cargando = false;
      });
    } catch (e) {
      console.log(e);
      this.cargando = false;
    }
  }

  
}

export interface IGenerica extends IAuto{}