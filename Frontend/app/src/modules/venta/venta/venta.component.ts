import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IAuto } from 'src/interfaces/IAuto';
import { ICliente } from 'src/interfaces/ICliente';
import { IMarca } from 'src/interfaces/IMarca';
import { IModelo } from 'src/interfaces/IModelo';
import { IPais } from 'src/interfaces/IPais';
import { IVendedor } from 'src/interfaces/IVendedor';
import { AutoService } from 'src/services/auto/auto.service';
import { ClienteService } from 'src/services/cliente/cliente.service';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { ModeloService } from 'src/services/modelo/modelo.service';
import { PaisService } from 'src/services/pais/pais.service';
import { VentaService } from 'src/services/venta/venta.service';

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css'],
})
export class VentaComponent {
  filterModelos!: IGenerica[];
  filterMarcas!: IGenerica[];
  listaModelos!: IGenerica[];
  listaMarcas!: IGenerica[];

  filtro: string = '';
  filtro1: string = '';
  filtro3: string = '';
  filtro4: string = '';

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
    private paisService: PaisService,
    public dialog: MatDialog
  ) {
    this.consultarMarcas();
    this.consultarModelos();
    this.consultarPaises();
    this.configurarFormulario();
  }

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
    idPais: [, Validators.required],
    fechaVenta: [],
    precio: ['', Validators.required],
    costo: [0],
    ganancia:[0],
    documento: [],
    nombre: [],
    apellido: [],
    auto: this.fb.group({
      id: [0],
      precio: [0],
      costo: [0],
      ganancia:[0],
      vendido: [],
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
    }),
    cliente: this.fb.group({
      id: [],
      nombre: [],
      apellido: [],
      documento: [],
    }),
    empleado: this.fb.group({
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
        ganancia: r.ganancia,
        idModelo: r.modelo.id,
        idMarca: r.modelo.marca.id,
        idPais: r.pais.id,
      });
    });
    console.log(this.registerForm.value)
  }

  jsonUser!: IVendedor;

  getVendedor() {
    const usuario = localStorage.getItem('user');
    if (usuario) {
      this.jsonUser = JSON.parse(usuario);
    }
  }

  setFormValues() {
    this.registerForm.value.auto.id = this.dataService.id;
    this.registerForm.value.auto.costo = this.registerForm.value.costo;
    this.registerForm.value.auto.precio = this.registerForm.value.precio;
    this.registerForm.value.auto.ganancia = this.registerForm.value.ganancia;
    this.registerForm.value.auto.modelo.id = this.modelo.id;
    this.registerForm.value.auto.modelo.nombreModelo = this.modelo.nombreModelo;
    this.registerForm.value.auto.modelo.marca.id = this.modelo.marca.id;
    this.registerForm.value.auto.modelo.marca.nombreMarca =
      this.modelo.marca.nombreMarca;
    this.registerForm.value.auto.pais.id = this.pais.id;
    this.registerForm.value.auto.pais.nombrePais = this.pais.nombrePais;
    this.registerForm.value.auto.pais.categoria.id = this.pais.categoria.id;
    this.registerForm.value.auto.pais.categoria.nombreCategoria =
      this.pais.categoria.nombreCategoria;
    this.registerForm.value.auto.pais.categoria.porcentaje =
      this.pais.categoria.porcentaje;
    this.registerForm.value.fechaVenta = new Date();
    this.registerForm.value.empleado.id = this.jsonUser.id;
  }

  async register() {
    this.getVendedor();
    this.setFormValues();

    console.log(this.registerForm.value);
    await this.service4.alta(this.registerForm.value).subscribe((data) => {
      this.dataService.object = data;
      console.log('Registro realizado con éxito');
      this.onNoClick();
    });
  }

  consultarModelos(): void {
    this.service1.consulta().subscribe((r: IGenerica[]) => {
      console.log(r);
      this.listaModelos = r;
    });
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

  displayMarcas(id: number) {
    console.log(id);
    if (!id) return '';

    this.obtenerModelosPorMarca(id);
    let index = this.listaMarcas.findIndex((r) => r.id === id);
    console.log('index', index);
    return this.listaMarcas[index].nombreMarca;
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
      f.nombrePais?.toLowerCase().trim().includes(this.filtro4)
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

  autoCompletarCliente() {
    let documento = this.registerForm.value.documento;

    console.log(documento);

    this.service3.consultaPorDoumento(documento).subscribe((r) => {
      console.log(r);
      this.registerForm.get('idCliente')?.setValue(r.id);
      this.registerForm.value.cliente.id = r.id;
      this.registerForm.get('nombre')?.setValue(r.nombre);
      this.registerForm.value.cliente.nombre = r.nombre;
      this.registerForm.get('apellido')?.setValue(r.apellido);
      this.registerForm.value.cliente.apellido = r.apellido;
      this.registerForm.value.cliente.documento = r.documento;
    });
  }
}

export interface IGenerica extends IMarca, IModelo, IAuto,ICliente, IPais{}
