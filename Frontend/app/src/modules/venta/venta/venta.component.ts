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
import { sweetalert } from 'src/utils/utils';

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
    }),
    cliente: this.fb.group({
      id: [0],
    }),
    empleado: this.fb.group({
      id: [0],
    }),
  });

  configurarFormulario() {
    if (this.dataService.id != 0) {
      this.autocompletar();
    }
  }

  autocompletar() {
    this.service.consultaPorId(this.dataService.id).subscribe((r) => {
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
    this.registerForm.value.fechaVenta = new Date();
    this.registerForm.value.empleado.id = this.jsonUser.id;
  }

  register() {
    this.getVendedor();
    this.setFormValues();

    this.service4.alta(this.registerForm.value).subscribe((data) => {
      sweetalert.success();
      this.dataService.object = data;
      this.onNoClick();
    });
  }

  consultarModelos(): void {
    this.service1.consulta().subscribe((r: IGenerica[]) => {
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
  }

  consultarMarcas(): void {
    this.service2.consulta().subscribe((r: IGenerica[]) => {
      this.listaMarcas = r;
      this.filterMarcas = r;
    });
  }

  filtrarMarcas() {
    if (this.filtro1 == '') {
      this.registerForm.get('idModelo')?.reset(); //Si el campo idMarca esta vacio, tambi??n vaciar el campo idModelo.
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

    this.service3.consultaPorDoumento(documento).subscribe((r) => {
      this.registerForm.value.cliente.id = r.id;  //Establezo del id del cliente
      this.registerForm.get('nombre')?.setValue(r.nombre); //Lleno los campos visibles del formulario.
      this.registerForm.get('apellido')?.setValue(r.apellido);
    });
  }
}

export interface IGenerica extends IMarca, IModelo, IAuto,ICliente, IPais{}
