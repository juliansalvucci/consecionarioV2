import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { IRango } from 'src/interfaces/IRango';
import { DataService } from 'src/services/data.service';
import { RangoService } from 'src/services/rango/rango.service';
import { RangoComponent } from '../rango/rango.component';

@Component({
  selector: 'app-rangolista',
  templateUrl: './rangolista.component.html',
  styleUrls: ['./rangolista.component.css']
})
export class RangolistaComponent implements OnInit {
  displayedColumns: string[] = ['montoInicial', 'montoFinal','valor', 'acciones'];
  dataSource!: MatTableDataSource<IGenerica>;

  cargando: boolean = false;
  filtro: string = '';

  listaFiltro!: IGenerica[];
  lista!: IGenerica[];

  constructor(
    public dialog: MatDialog,
    private service: RangoService,
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

  consultar() {
    this.service.consulta().subscribe((r: IGenerica[]) => {
      console.log(r);
      this.lista = r;
      this.dataSource = new MatTableDataSource(this.lista);
      this.configTable();
      this.cargando = false;
    });
  }

  abrirModal(id: number): void {
    this.dataService.id = id;
    const dialogRef = this.dialog.open(RangoComponent, {
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

  eliminar(id: number) {
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
      this.listaFiltro = this.lista?.filter((f) =>
        f.valor.toString()
          ?.toLowerCase()
          .trim()
          .includes(this.filtro.toLocaleLowerCase())
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }

}

export interface IGenerica extends IRango{}