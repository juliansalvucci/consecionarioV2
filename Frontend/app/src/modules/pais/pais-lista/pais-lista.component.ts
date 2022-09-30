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
  constructor(
    public dialog: MatDialog,
    private service: PaisService,
    private dataService: DataService,
    public _MatPaginatorIntl: MatPaginatorIntl
  ) 
  {
    this.consultar();
  }

  ngOnInit(): void 
  {
    this._MatPaginatorIntl.itemsPerPageLabel = 'items por página';
  }

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  
  
  configTable() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  displayedColumns: string[] = ['nombrePais', 'acciones'];
  dataSource!: MatTableDataSource<IGenerica>;

  cargando: boolean = false;
  lista!: IPais[];

  consultar(): void {
    this.service.consulta().subscribe((r: IGenerica[]) => {
      console.log(r);
      this.lista = r;
      this.dataSource = new MatTableDataSource(this.lista);
      this.configTable();
      this.cargando = false;
    });
  }

  abrirModal(id:number): void {
    this.dataService.id = id;
    const dialogRef = this.dialog.open(PaisComponent, {
      width: '450px',
    });
    dialogRef.afterClosed().subscribe(result => {

      if (this.dataService.object != null)
      {
        this.lista = this.lista.filter((element) => element.id != id);
        this.lista.push(this.dataService.object);
        this.dataSource = new MatTableDataSource(this.lista);
        this.configTable();
        this.dataService.object = null
      }
      this.cargando = false;
    });
  }


  eliminar(id: number) {
    this.service.baja(id).subscribe((r) => {
      if(r){
        this.lista = this.lista.filter(element => element.id != id)
        this.dataSource = new MatTableDataSource(this.lista);
        this.configTable();
      }
      this.cargando = false;
    }, e => {
      console.log(e)
    });
  }

  listaFiltro!: IGenerica[]
  filtro: string = ''

  filtrar()
  {
    if(this.filtro == "")
    {
      this.listaFiltro = this.lista;
    } 
    else
    {
      this.listaFiltro = this.lista?.filter(f => f.nombrePais?.toLowerCase().trim().includes(this.filtro.toLocaleLowerCase()));
    }
    this.dataSource = new MatTableDataSource(this.listaFiltro);
    this.configTable();
  }
}

export interface IGenerica extends IPais{}

//Datos útiles

//this.lista = this.lista.map(objeto => objeto.id === id ? this.dataService.object : objeto)  Actualiza la lista solo para elementos modificados, pero no para nuevos, por eso se descarta.