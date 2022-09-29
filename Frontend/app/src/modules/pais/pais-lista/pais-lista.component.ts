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
    private paisService: PaisService,
    private data: DataService,
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
    this.paisService.consultarPaises().subscribe((r: IPais[]) => {
      console.log(r);
      this.lista = r;
      this.dataSource = new MatTableDataSource(this.lista);
      this.cargando = false;
    });
  }

  modificar(id:number): void {
    this.data.id = id;
    const dialogRef = this.dialog.open(PaisComponent, {
      width: '450px',
    });

    dialogRef.afterClosed().subscribe(result => {
      this.consultar;
      this.cargando = false;
    });
  }


  eliminar(id: number) {
    this.paisService.bajaPais(id).subscribe((r) => {
      this.consultar();
      this.cargando = false;
    });
  }
}
