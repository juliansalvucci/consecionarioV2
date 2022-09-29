import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginatorIntl } from '@angular/material/paginator';
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

  ngOnInit(): void {}

  displayedColumns: string[] = ['nombrePais', 'acciones'];
  dataSource!: MatTableDataSource<IPais>;

  cargando: boolean = false;
  lista!: IPais[];

  consultar(): void {
    this.service.consulta().subscribe((r: IPais[]) => {
      console.log(r);
      this.lista = r;
      this.dataSource = new MatTableDataSource(this.lista);
      this.cargando = false;
    });
  }

  abrirModal(id:number): void {
    this.dataService.id = id;
    const dialogRef = this.dialog.open(PaisComponent, {
      width: '450px',
    });

    dialogRef.afterClosed().subscribe(result => {
      this.lista.push(this.dataService.object);
      this.dataSource = new MatTableDataSource(this.lista);
      this.cargando = false;
    });
  }

  eliminar(id: number) {
    this.service.baja(id).subscribe((r) => {
      this.lista.splice(id) //Quito el elemento de la lista.
      this.cargando = false;
    }, e => {
      this.lista = this.lista.filter(element => element.id != id)
      this.dataSource = new MatTableDataSource(this.lista);
    });
  }
}
