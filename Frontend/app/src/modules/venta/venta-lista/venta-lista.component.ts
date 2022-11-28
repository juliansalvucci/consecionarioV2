import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
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
  styleUrls: ['./venta-lista.component.css'],
})
export class VentaListaComponent implements OnInit {
  displayedColumns: string[] = [
    'fecha',
    'modelo',
    'marca',
    'categoria',
    'precio',
    'costo',
    'ganancia',
    'porcentaje',
    'cliente',
    'empleado',
  ];
  dataSource!: MatTableDataSource<IGenerica>;

  cargando: boolean = false;
  filtro1: string = '';
  filtro2: string = '';
  filtro3: string = '';
  filtro4: string = '';
  filtro5: string = '';

  listaFiltro!: IGenerica[];
  lista!: IGenerica[];

  registerForm = this.fb.group({
    fechaDesde: [''],
    fechaHasta: [''],
    montoInicial: [0],
    montoFinal: [0],
  });

  constructor(
    public dialog: MatDialog,
    private service: VentaService,
    private dataService: DataService,
    public _MatPaginatorIntl: MatPaginatorIntl,
    private fb: FormBuilder
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
      this.lista = r;
      this.dataSource = new MatTableDataSource(this.lista);
      this.configTable();
      this.cargando = false;
    });
  }

  buscar() {
    console.log(this.registerForm.value);
    this.service
      .consultaAvanzada(this.registerForm.value)
      .subscribe((r: IGenerica[]) => {
        console.log(r);
        this.lista = r;
        this.dataSource = new MatTableDataSource(this.lista);
        this.configTable();
        this.cargando = false;
      });
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

  filtraPorMarca() {
    if (this.filtro1 == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter(
        (f) =>
          f.auto?.modelo?.marca?.nombreMarca
            ?.toLowerCase()
            .trim()
            .includes(this.filtro1.toLowerCase())
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }

  filtrarPorModelo() {
    if (this.filtro2 == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter(
        (f) =>
          f.auto?.modelo?.nombreModelo
            ?.toLowerCase()
            .trim()
            .includes(this.filtro2.toLocaleLowerCase()) 
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }

  filtrarPorCategoria() {
    if (this.filtro3 == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter(
        (f) =>
          f.auto?.pais?.categoria?.nombreCategoria
            ?.toLowerCase()
            .trim()
            .includes(this.filtro3.toLocaleLowerCase()) 
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }

  filtrarPorCliente() {
    if (this.filtro4 == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter(
        (f) =>
          f.cliente?.nombre
            ?.toLowerCase()
            .trim()
            .includes(this.filtro4.toLocaleLowerCase()) 
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }

  filtrarPorVendedor() {
    if (this.filtro5 == '') {
      this.listaFiltro = this.lista;
    } else {
      this.listaFiltro = this.lista?.filter(
        (f) =>
          f.vendedor?.nombre
            ?.toLowerCase()
            .trim()
            .includes(this.filtro5.toLocaleLowerCase()) 
      );
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }

}

export interface IGenerica extends IVenta{}
