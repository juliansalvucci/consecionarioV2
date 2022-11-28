import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import * as moment from 'moment';
import { ICategoria } from 'src/interfaces/ICategoria';
import { ICliente } from 'src/interfaces/ICliente';
import { IMarca } from 'src/interfaces/IMarca';
import { IModelo } from 'src/interfaces/IModelo';
import { IVendedor } from 'src/interfaces/IVendedor';
import { IVenta } from 'src/interfaces/IVenta';
import { CategoriaService } from 'src/services/categoria/categoria.service';
import { ClienteService } from 'src/services/cliente/cliente.service';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { ModeloService } from 'src/services/modelo/modelo.service';
import { VendedorService } from 'src/services/vendedor/vendedor.service';
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

  date = new Date();
  yesterDay!:string
  now!:string

  registerForm = this.fb.group({
    fechaDesde: [this.yesterDay],
    fechaHasta: [this.now],
    montoInicial: [0],
    montoFinal: [0],
    idMarca:[0],
    idModelo:[0],
    idCliente:[0],
    idVendedor:[0],
    idCategoria:[0],
  });

  constructor(
    public dialog: MatDialog,
    private service: VentaService,
    private categoriaService:CategoriaService,
    private modeloService:ModeloService,
    private marcaService: MarcaService,
    private clienteService: ClienteService,
    private vendedorService: VendedorService,
    private dataService: DataService,
    public _MatPaginatorIntl: MatPaginatorIntl,
    private fb: FormBuilder
  ) {
    
    //Defino el intervalo de 24 horas, para trael los registros en ese intervalo.

    this.registerForm.get('fechaHasta')?.setValue(moment(this.date)
    .tz('America/Argentina/Cordoba') 
    .format());

    this.date.setDate(this.date.getDate()-1)
    this.registerForm.get('fechaDesde')?.setValue(moment(this.date)
      .tz('America/Argentina/Cordoba') 
      .format());
    

    setTimeout(()=>{this.buscar()},100)
    
    this.consultarCategorias();
    this.consultarModelos();
    this.consultarMarcas();
    this.consultarClientes();
    this.consultarVendedores();
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

    this.registerForm.value.fechaDesde = moment(this.registerForm.value.fechaDesde)
      .tz('America/Argentina/Cordoba') 
      .format();
    this.registerForm.value.fechaHasta = moment(this.registerForm.value.fechaHasta)
      .tz('America/Argentina/Cordoba')
      .format();

    console.log(this.registerForm.value);
    this.service
      .busquedaAvanzada(this.registerForm.value)
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

  
  listaClientes!: ICliente[];
  filterClientes!: ICliente[];
  filtroClientes: string = '';

  consultarClientes(): void {
    this.clienteService.consulta().subscribe((r: ICliente[]) => {
      console.log(r);
      this.listaClientes = r;
      this.filterClientes = r;
    });
  }

  filtrarClientes() {
    this.filterClientes = this.listaClientes?.filter((f) =>
      f.nombre?.toLowerCase().trim().includes(this.filtroClientes) ||
      f.apellido?.toLocaleLowerCase().trim().includes(this.filtroClientes)
    );
  }

  displayClientes(id: number) {
    console.log(id);

    if (!id) return '';

    this.registerForm.value.idCliente = id;

    let index = this.listaClientes.findIndex((r) => r.id === id);
    console.log('index', index);

    let nombre = this.listaClientes[index].nombre;
    let apellido = this.listaClientes[index].apellido;


    return apellido +' '+ nombre;
  }

  listaVendedores!: IVendedor[];
  filterVendedores!: IVendedor[];
  filtroVendedores: string = ''

  consultarVendedores(): void {
    this.vendedorService.consulta().subscribe((r: IVendedor[]) => {
      console.log(r);
      this.listaVendedores = r;
      this.filterVendedores = r;
    });
  }

  filtrarVendedores() {
    this.filterVendedores = this.listaVendedores?.filter((f) =>
      f.nombre?.toLowerCase().trim().includes(this.filtroVendedores)
    );
  }


  displayVendedores(id: number) {
    console.log(id);

    if (!id) return '';

    let index = this.listaVendedores.findIndex((r) => r.id === id);
    console.log('index', index);

    let nombre = this.listaVendedores[index].nombre;
    let apellido = this.listaVendedores[index].apellido;

    return apellido + ' ' +nombre;
  }

  listaMarcas!: IMarca[];
  filterMarcas!: IMarca[];
  filtroMarcas: string = '';

  consultarMarcas(): void {
    this.marcaService.consulta().subscribe((r: IMarca[]) => {
      console.log(r);
      this.listaMarcas = r;
      this.filterMarcas = r;
    });
  }

  filtrarMarcas() {
    this.filterMarcas = this.listaMarcas?.filter((f) =>
      f.nombreMarca?.toLowerCase().trim().includes(this.filtroMarcas)
    );
  }

  displayMarcas(id: number) {
    console.log(id);

    if (!id) return '';

    let index = this.listaMarcas.findIndex((r) => r.id === id);
    console.log('index', index);

    let nombre = this.listaMarcas[index].nombreMarca;

    return nombre;
  }

  listaModelos!: IModelo[];
  filterModelos!: IModelo[];
  filtroModelos: string = '';

  consultarModelos(): void {
    this.modeloService.consulta().subscribe((r: IModelo[]) => {
      console.log(r);
      this.listaModelos = r;
      this.filterModelos = r;
    });
  }

  filtrarModelos() {
    this.filterModelos = this.listaModelos?.filter((f) =>
      f.nombreModelo?.toLowerCase().trim().includes(this.filtroModelos)
    );
  }

  displayModelos(id: number) {
    console.log(id);

    if (!id) return '';

    let index = this.listaModelos.findIndex((r) => r.id === id);
    console.log('index', index);

    let nombre = this.listaModelos[index].nombreModelo;

    return nombre;
  }


  listaCategorias!: ICategoria[];
  filterCategorias!: ICategoria[];
  filtroCategorias: string = ''

  consultarCategorias(): void {
    this.categoriaService.consulta().subscribe((r: ICategoria[]) => {
      console.log(r);
      this.listaCategorias = r;
      this.filterCategorias = r;
    });
  }

  filtrarCategorias() {
    this.filterCategorias = this.listaCategorias?.filter((f) =>
      f.nombreCategoria?.toLowerCase().trim().includes(this.filtroCategorias)
    );
  }

  displayCategoria(id: number) {
    console.log(id);

    if (!id) return '';

    let index = this.listaCategorias.findIndex((r) => r.id === id);
    console.log('index', index);

    let nombre = this.listaCategorias[index].nombreCategoria;

    return nombre;
  }

}

export interface IGenerica extends IVenta{}
