import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { IPais } from 'src/interfaces/IPais';
import { DataService } from 'src/services/data.service';
import { PaisService } from 'src/services/pais.service';
import { PaisComponent } from '../pais/pais.component';


@Component({
  selector: 'app-pais-lista',
  templateUrl: './pais-lista.component.html',
  styleUrls: ['./pais-lista.component.css'],
})
export class PaisListaComponent implements OnInit {
  displayedColumns: string[] = ['nombrePais', 'acciones'];
  dataSource!: MatTableDataSource<IGenerica>;

  cargando: boolean = false;
  filtro: string = '';

  listaFiltro!: IGenerica[];
  lista!: IGenerica[];

  constructor(
    public dialog: MatDialog,
    private service: PaisService,
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
    const dialogRef = this.dialog.open(PaisComponent, {
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

  filtrar() {
    if (this.filtro == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter((f) =>
        f.nombrePais
          ?.toLowerCase()
          .trim()
          .includes(this.filtro.toLocaleLowerCase())
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }
}

export interface IGenerica extends IPais{}

