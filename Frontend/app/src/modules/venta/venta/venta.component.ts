import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IAuto } from 'src/interfaces/IAuto';
import { ICliente } from 'src/interfaces/ICliente';
import { IMarca } from 'src/interfaces/IMarca';
import { IModelo } from 'src/interfaces/IModelo';
import { AutoService } from 'src/services/auto/auto.service';
import { ClienteService } from 'src/services/cliente/cliente.service';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { ModeloService } from 'src/services/modelo/modelo.service';
import { VentaService } from 'src/services/venta/venta.service';

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css'],
})
export class VentaComponent implements OnInit {
  filterModelos!: IGenerica[];
  filterMarcas!: IGenerica[];
  listaModelos!: IGenerica[];
  listaMarcas!: IGenerica[];

  filtro: string = '';
  filtro1: string = '';
  filtro3: string = '';

  idModelo: number = 0;

  constructor(
    public dialogRef: MatDialogRef<IGenerica>,
    @Inject(MAT_DIALOG_DATA) public data: IGenerica,
    private service: AutoService,
    private service1: ModeloService,
    private service2: MarcaService,
    private service3: ClienteService,
    private service4: VentaService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.consultarClientes();
    this.consultarMarcas();
    this.consultarModelos();
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
    idModelo: [, Validators.required],
    idMarca: [, Validators.required],
    idCliente: [, Validators.required],
    fechaVenta: [],
    precio: ['', Validators.required],
    costo: [0],
    porcentaje: [0],
    auto: this.fb.group({
      id: [0],
      precio: [0],
      costo: [0],
      vendido: [],
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
    }),
    cliente: this.fb.group({
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
        costo: r.costo,
        porcentaje: r.modelo.marca.pais.categoria.porcentaje,
        idModelo: r.modelo.id,
        idMarca: r.modelo.marca.id,
      });
    });
  }

  setFormValues() {
    this.registerForm.value.auto.id = this.dataService.id;
    this.registerForm.value.auto.vendido = true;
    this.registerForm.value.auto.modelo.id = this.modelo.id;
    this.registerForm.value.auto.modelo.nombreModelo = this.modelo.nombreModelo;
    this.registerForm.value.auto.modelo.marca.id = this.modelo.marca.id;
    this.registerForm.value.auto.modelo.marca.nombreMarca = this.modelo.marca.nombreMarca;
    this.registerForm.value.auto.modelo.marca.pais.id = this.modelo.marca.pais.id;
    this.registerForm.value.auto.modelo.marca.pais.nombrePais = this.modelo.marca.pais.nombrePais;
    this.registerForm.value.auto.modelo.marca.pais.categoria.id = this.modelo.marca.pais.categoria.id;
    this.registerForm.value.auto.modelo.marca.pais.categoria.nombreCategoria = this.modelo.marca.pais.categoria.nombreCategoria;
    this.registerForm.value.auto.modelo.marca.pais.categoria.porcentaje = this.modelo.marca.pais.categoria.porcentaje;

    this.registerForm.value.cliente.id = this.registerForm.value.idCliente;
    this.registerForm.value.fechaVenta = new Date();
  }

  async register() {
    try {

      this.setFormValues();

      console.log(this.registerForm.value);
      this.service4.alta(this.registerForm.value).subscribe((data) => {
        this.registerAuto();
        this.dataService.object = data;
        console.log('Registro realizado con éxito');
        this.onNoClick();
      });
    } catch (e) {
      console.log(this.registerForm.value);
      console.log('modelo invalido');
      this.onNoClick();
    }
  }

  //Función provisoria para setear el estado vendido del auto.
  async registerAuto() {
    try {
      console.log(this.registerForm.value);
      this.service.alta(this.registerForm.value.auto).subscribe((data) => {
          console.log('Registro realizado con éxito');
          this.onNoClick();
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

  displayMarcas(id: number) {
    console.log(id);
    if (!id) return '';

    this.obtenerModelosPorMarca(id);
    let index = this.listaMarcas.findIndex((r) => r.id === id);
    console.log('index', index);
    return this.listaMarcas[index].nombreMarca;
  }

  listaClientes!: IGenerica[];
  filterClientes!: IGenerica[];
  consultarClientes() {
    try {
      this.service3.consulta().subscribe((r: IGenerica[]) => {
        console.log(r);
        this.listaClientes = r;
        this.filterClientes = r;
      });
    } catch (e) {
      console.log(e);
    }
  }

  filtrarClientes() {
    if (this.filtro3 == '') {
      this.filterClientes = this.listaClientes;
    } else {
      this.filterClientes = this.listaClientes?.filter((f) =>
        f.nombreCliente?.toLowerCase().trim().includes(this.filtro3)
      );
    }
  }

  displayClientes(id: number) {
    console.log(id);
    if (!id) return '';

    this.obtenerModelosPorMarca(id);
    let index = this.listaClientes.findIndex((r) => r.id === id);
    console.log('index', index);
    return this.listaClientes[index].nombreCliente;
  }
}

export interface IGenerica extends IMarca, IModelo, IAuto,ICliente{}
