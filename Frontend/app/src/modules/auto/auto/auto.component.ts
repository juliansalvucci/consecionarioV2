import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IAuto } from 'src/interfaces/IAuto';
import { IMarca } from 'src/interfaces/IMarca';
import { IModelo } from 'src/interfaces/IModelo';
import { AutoService } from 'src/services/auto/auto.service';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { ModeloService } from 'src/services/modelo/modelo.service';

@Component({
  selector: 'app-auto',
  templateUrl: './auto.component.html',
  styleUrls: ['./auto.component.css'],
})
export class AutoComponent implements OnInit {
  filterItems!: IGenerica[];
  filterItems1!: IGenerica[];
  lista!: IGenerica[];
  lista1!: IGenerica[];

  filtro: string = '';
  filtro1: string = '';

  idModelo: number = 0;

  constructor(
    public dialogRef: MatDialogRef<IGenerica>,
    @Inject(MAT_DIALOG_DATA) public data: IGenerica,
    private service: AutoService,
    private service1: ModeloService,
    private service2: MarcaService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.consultarMarcas();
    this.consultarModelos();
    this.configurarFormulario();
  }

  ngOnInit(): void {}

  displayModelos(id: number) {
    console.log(id);
    this.registerForm.value.modelo.id = id; //seteo el id del objeto modelo.
    if (!id) return '';

    let index = this.lista.findIndex((r) => r.id === id);
    console.log('index', index);
    return this.lista[index].nombreModelo;
  }

  registerForm = this.fb.group({
    id: [0],
    precio: ['',Validators.required],
    idModelo: [,Validators.required],
    idMarca: [,Validators.required],
    modelo: this.fb.group({
      id: [],
    }),
  });

  configurarFormulario() {
    if (this.dataService.id != 0) {
      this.autocompletar();
    }
  }

  autocompletar() {
    this.service.consultaPorId(this.dataService.id).subscribe((r) => {
      console.log(r);
      this.registerForm.patchValue({
        id: r.id,
        precio: r.precio,
        idModelo: r.modelo.id,
        idMarca: r.modelo.marca.id,
      });
    });
  }

  register() {
    try {
      this.service.alta(this.registerForm.value).subscribe((data) => {
        this.service.consultaPorId(data.id).subscribe((data) => {
          this.dataService.object = data;
          console.log('Registro realizado con éxito');
          this.onNoClick();
        });
      });
    } catch (e) {
      console.log(this.registerForm.value);
      console.log('modelo invalido');
      this.onNoClick();
    }
  }

  consultarModelos(): void {
    try {
      this.service1.consulta().subscribe((r: IGenerica[]) => {
        console.log(r);
        this.lista = r;
      });
    } catch (e) {
      console.log(e);
    }
  }

  filtrarModelos() {
    this.filterItems = this.lista?.filter((f) =>
      f.nombreModelo?.toLowerCase().trim().includes(this.filtro)
    );
  }

  obtenerModelosPorMarca(id: number) {
    console.log(id, 'trolo');

    this.filterItems = this.lista;

    if (id != null) {
      this.filterItems = this.filterItems.filter((f) => f.idMarca === id);
    }
    console.log(this.filterItems);
  }

  consultarMarcas(): void {
    try {
      this.service2.consulta().subscribe((r: IGenerica[]) => {
        console.log(r);
        this.lista1 = r;
        this.filterItems1 = r;
      });
    } catch (e) {
      console.log(e);
    }
  }

  filtrarMarcas() {
    this.filterItems1 = this.lista1?.filter((f) =>
      f.nombreMarca?.toLowerCase().trim().includes(this.filtro1)
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  displayMarcas(id: number) {
    console.log(id);
    if (!id) return '';

    this.obtenerModelosPorMarca(id);
    let index = this.lista1.findIndex((r) => r.id === id);
    console.log('index', index);
    return this.lista1[index].nombreMarca;
  }
}

export interface IGenerica extends IMarca, IModelo, IAuto{}
