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
  filterModelos!: IGenerica[];
  filterMarcas!: IGenerica[];
  listaModelos!: IGenerica[];
  listaMarcas!: IGenerica[];

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

  modelo!: IModelo;

  displayModelos(id: number) {
    console.log(id);
    this.registerForm.value.modelo.id = id; //seteo el id del objeto modelo.
    if (!id) return '';

    let index = this.listaModelos.findIndex((r) => r.id === id);
    console.log('index', index);

    this.modelo = this.listaModelos[index];

    return this.modelo.nombreModelo;
  }

  registerForm = this.fb.group({
    id: [0],
    precio: ['', Validators.required],
    costo: [0],
    vendido: [false],
    idModelo: [, Validators.required],
    idMarca: [, Validators.required],
    modelo: this.fb.group({
      id: [0],
      nombreModelo: [''],
      marca: this.fb.group({
        id: [0],
        nombreMarca: [''],
        pais: this.fb.group({
          id: [0],
          nombrePais: [''],
          categoria: this.fb.group({
            id: [0],
            nombreCategoria: [''],
            porcentaje: [''],
          }),
        }),
      }),
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

  setFormValues() {
    this.registerForm.value.modelo.id = this.modelo.id;
    this.registerForm.value.modelo.nombreModelo = this.modelo.nombreModelo;
    this.registerForm.value.modelo.marca.id = this.modelo.marca.id;
    this.registerForm.value.modelo.marca.nombreMarca =
      this.modelo.marca.nombreMarca;
    this.registerForm.value.modelo.marca.pais.id = this.modelo.marca.pais.id;
    this.registerForm.value.modelo.marca.pais.nombrePais =
      this.modelo.marca.pais.nombrePais;
    this.registerForm.value.modelo.marca.pais.categoria.id =
      this.modelo.marca.pais.categoria.id;
    this.registerForm.value.modelo.marca.pais.categoria.nombreCategoria =
      this.modelo.marca.pais.categoria.nombreCategoria;
    this.registerForm.value.modelo.marca.pais.categoria.porcentaje =
      this.modelo.marca.pais.categoria.porcentaje;
  }

  async register() {
    try {
      this.setFormValues();

      console.log(this.registerForm.value);
      this.service.alta(this.registerForm.value).subscribe((data) => {
        this.service.calcularCosto(data).subscribe((data) => {
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
        this.listaModelos = r;
      });
    } catch (e) {
      console.log(e);
    }
  }

  filterModelosAux!: IGenerica[];

  filtrarModelos() {
    if (this.filtro == '') {
      this.filterModelos == this.listaModelos;
    } else {
      this.filterModelos = this.filterModelosAux?.filter((f) =>
        f.nombreModelo?.toLowerCase().trim().includes(this.filtro)
      );
    }
  }

  obtenerModelosPorMarca(id: number) {
    if (id != null) {
      this.filterModelosAux = this.listaModelos.filter((f) => f.marca.id == id);
    }
    console.log(this.filterModelos);
  }

  consultarMarcas(): void {
    try {
      this.service2.consulta().subscribe((r: IGenerica[]) => {
        console.log(r);
        this.listaMarcas = r;
        this.filterMarcas = r;
      });
    } catch (e) {
      console.log(e);
    }
  }

  filtrarMarcas() {
    if (this.filtro1 == '') {
      this.registerForm.get('idModelo')?.reset(); //Si el campo idMarca esta vacio, también vaciar el campo idModelo.
      this.filterMarcas = this.listaMarcas;
    } else {
      this.filterMarcas = this.listaMarcas?.filter((f) =>
        f.nombreMarca?.toLowerCase().trim().includes(this.filtro1)
      );
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  marca!: IMarca;

  displayMarcas(id: number) {
    console.log(id);
    if (!id) return '';

    this.obtenerModelosPorMarca(id);
    let index = this.listaMarcas.findIndex((r) => r.id === id);
    console.log('index', index);

    this.marca = this.listaMarcas[index];

    return this.marca.nombreMarca;
  }
}

export interface IGenerica extends IMarca, IModelo, IAuto{}
