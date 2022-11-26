import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Color, ScaleType } from '@swimlane/ngx-charts';
import * as moment from 'moment';
import { IGananciaYCantidadPorMarca } from 'src/interfaces/IGananaciaYCantidadPorMarca';
import { ReportesService } from 'src/services/reportes/reportes.service';

export interface data{
  name:string
  value:string
}

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
  listaGrafica: data[] = [];

  registerForm = this.fb.group({
    fechaDesde: [''],
    fechaHasta: [''],
  });

  constructor(
    private service: ReportesService,
    public _MatPaginatorIntl: MatPaginatorIntl,
    private fb: FormBuilder,
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
    try {
      let fechaDesde = moment(this.registerForm.value.fechaDesde).tz('America/Argentina/Cordoba')
      .format();
      let fechaHasta = moment(this.registerForm.value.fechaHasta).tz('America/Argentina/Cordoba')
      .format();
      
     this.service.consulta(fechaDesde,fechaHasta).subscribe((r: IGenerica[]) => {
        console.log(r);
        this.lista = r;
        this.dataSource = new MatTableDataSource(this.lista);
        this.generarData();
        this.configTable();
        this.cargando = false;
      });
    } catch (e) {
      console.log(e);
      this.cargando = false;
    }
  }

  generarData(){
    this.lista.forEach((element)=>{

        let obj = {
          name:element.marca,
          value:element.costo,
        }
        console.log("torta",obj)
        this.listaGrafica.push(obj)
        console.log("fuente",this.listaGrafica)
        
    })
    Object.assign(this.listaGrafica,this.listaGrafica)
    console.log(Object)
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

  view: [number,number] = [700, 400];

  // options
  gradient: boolean = true;
  showLegend: boolean = true;
  showLabels: boolean = true;
  isDoughnut: boolean = false;

  single = [
    {
      "name": "Germany",
      "value": 8940000
    },
    {
      "name": "USA",
      "value": 5000000
    },
    {
      "name": "France",
      "value": 7200000
    },
      {
      "name": "UK",
      "value": 6200000
    }
  ];

  colorScheme: Color = {
    name: 'myScheme',
    selectable: true,
    group: ScaleType.Ordinal,
    domain: ['#f00', '#0f0', '#0ff'],
  };


  onSelect(data: any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

}

export interface IGenerica extends IGananciaYCantidadPorMarca{}