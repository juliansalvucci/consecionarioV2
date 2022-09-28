import { Component, OnInit } from '@angular/core';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { IPais } from 'src/interfaces/IPais';
import { PaisService } from 'src/services/pais.service';

@Component({
  selector: 'app-pais-lista',
  templateUrl: './pais-lista.component.html',
  styleUrls: ['./pais-lista.component.css']
})
export class PaisListaComponent implements OnInit {

  constructor(private paisService: PaisService,public _MatPaginatorIntl: MatPaginatorIntl)
  {
    this.consultarPaises();
  }

  ngOnInit(): void {
  }

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource!:MatTableDataSource<IPais>


  cargando: boolean = false;
  lista!: IPais[];
  

  consultarPaises(): void {
    this.paisService.consultarPaises()
    .subscribe((r:IPais[]) => {
      console.log(r)
      this.lista = r;
      this.dataSource = new MatTableDataSource(this.lista);
      this.cargando = false;
    })
  }
}
