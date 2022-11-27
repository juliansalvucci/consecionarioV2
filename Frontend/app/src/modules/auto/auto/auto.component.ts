import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { IAuto } from 'src/interfaces/IAuto';
import { IMarca } from 'src/interfaces/IMarca';
import { IModelo } from 'src/interfaces/IModelo';
import { IPais } from 'src/interfaces/IPais';
import { MarcaComponent } from 'src/modules/marca/marca/marca.component';
import { ModeloComponent } from 'src/modules/modelo/modelo/modelo.component';
import { PaisComponent } from 'src/modules/pais/pais/pais.component';
import { AutoService } from 'src/services/auto/auto.service';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { ModeloService } from 'src/services/modelo/modelo.service';
import { PaisService } from 'src/services/pais/pais.service';

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

  filtro1: string = '';
  filtro2: string = '';
  filtro3: string = '';

  idModelo: number = 0;

  constructor(
    public dialogRef: MatDialogRef<IGenerica>,
    @Inject(MAT_DIALOG_DATA) public data: IGenerica,
    private service: AutoService,
    private service1: ModeloService,
    private service2: MarcaService,
    private paisService: PaisService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.consultarMarcas();
    this.consultarModelos();
    this.consultarPaises();
    this.configurarFormulario();
  }

  ngOnInit(): void {}

  modelo!: IModelo;

  displayModelos(id: number) {
    console.log(id);
    if (!id) return '';

    let index = this.listaModelos.findIndex((r) => r.id === id);
    console.log('index', index);

    this.modelo = this.listaModelos[index];

    return this.modelo.nombreModelo;
  }

  registerForm = this.fb.group({
    id: [0],
    precio: [0, [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/)]],
    costo: [0, [Validators.pattern(/^-?(0|[1-9]\d*)?$/)]],
    vendido: [],
    idModelo: [, Validators.required],
    idMarca: [, Validators.required],
    idPais: [, Validators.required],
    modelo: this.fb.group({
      id: [0],
      nombreModelo: [''],
      marca: this.fb.group({
        id: [0],
        nombreMarca: [''],
      }),
    }),
    pais: this.fb.group({
      id: [0],
      nombrePais: [''],
      categoria: this.fb.group({
        id: [0],
        nombreCategoria: [''],
        porcentaje: [''],
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
        idPais: r.pais.id,
      });
    });
  }

  setFormValues() {
    this.registerForm.value.vendido = false;
    this.registerForm.value.modelo.id = this.modelo.id;
    this.registerForm.value.modelo.nombreModelo = this.modelo.nombreModelo;
    this.registerForm.value.modelo.marca.id = this.modelo.marca.id;
    this.registerForm.value.modelo.marca.nombreMarca =
      this.modelo.marca.nombreMarca;
    this.registerForm.value.pais.id = this.pais.id;
    this.registerForm.value.pais.nombrePais = this.pais.nombrePais;
    this.registerForm.value.pais.categoria.id = this.pais.categoria.id;
    this.registerForm.value.pais.categoria.nombreCategoria =
      this.pais.categoria.nombreCategoria;
    this.registerForm.value.pais.categoria.porcentaje =
      this.pais.categoria.porcentaje;
  }

  async register() {
    this.setFormValues();

    console.log(this.registerForm.value);
    this.service.alta(this.registerForm.value).subscribe((data) => {
      this.dataService.object = data;
      console.log('Registro realizado con éxito');
      this.onNoClick();
    });
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
    if (this.filtro2 == '') {
      this.filterModelos == this.listaModelos;
    } else {
      this.filterModelos = this.filterModelosAux?.filter((f) =>
        f.nombreModelo?.toLowerCase().trim().includes(this.filtro2)
      );
    }
  }

  obtenerModelosPorMarca(id: number) {
    if (id != null) {
      this.filterModelosAux = this.listaModelos.filter((f) => f.marca.id == id);
    }
  }

  consultarMarcas(): void {
    this.service2.consulta().subscribe((r: IGenerica[]) => {
      console.log(r);
      this.listaMarcas = r;
      this.filterMarcas = r;
    });
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

  listaPaises!: IPais[];
  filterPaises!: IPais[];

  consultarPaises() {
    this.paisService.consulta().subscribe((r: IGenerica[]) => {
      console.log('Países', r);
      this.listaPaises = r;
      this.filterPaises = this.listaPaises;
    });
  }

  filtrarPaises() {
    this.filterPaises = this.listaPaises?.filter((f) =>
      f.nombrePais?.toLowerCase().trim().includes(this.filtro3)
    );
  }

  pais!: IPais;

  displayPaises(id: number) {
    if (!id) return '';

    let index = this.listaPaises.findIndex((r) => r.id === id);
    console.log('index', index);

    this.pais = this.listaPaises[index];

    return this.pais.nombrePais;
  }

  abrirModalPais(): void {
    this.onNoClick();
    const dialogRef = this.dialog.open(PaisComponent, {
      width: '450px',
    });
    dialogRef.afterClosed().subscribe(() => {
      this.abrirModalAuto();
    });
  }

  abrirModalAuto(): void {
    const dialogRef = this.dialog.open(AutoComponent, {
      width: '450px',
    });
  }

  abrirModalMarca(): void {
    this.onNoClick();
    const dialogRef = this.dialog.open(MarcaComponent, {
      width: '450px',
    });
    dialogRef.afterClosed().subscribe(() => {
      this.abrirModalAuto();
    });
  }

  mensaje!: String;
  abrirModalModelo(): void {
    if (this.registerForm.get('idMarca')?.value != 0) {
      this.onNoClick();
      this.dataService.idMarca = this.registerForm.get('idMarca')?.value;
      const dialogRef = this.dialog.open(ModeloComponent, {
        width: '450px',
      });
      dialogRef.afterClosed().subscribe(() => {
        this.abrirModalAuto();
      });
    } else {
      this.mensaje = 'Seleccione una marca';
    }
  }
}

export interface IGenerica extends IMarca, IModelo, IAuto, IPais {}
