import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IMarca } from 'src/interfaces/IMarca';
import { IModelo } from 'src/interfaces/IModelo';
import { MarcaComponent } from 'src/modules/marca/marca/marca.component';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { ModeloService } from 'src/services/modelo/modelo.service';
import { sweetalert } from 'src/utils/utils';

@Component({
  selector: 'app-modelo',
  templateUrl: './modelo.component.html',
  styleUrls: ['./modelo.component.css'],
})
export class ModeloComponent {
  filterItems!: IGenerica[];
  lista!: IGenerica[];

  filtro: string = '';

  constructor(
    public dialogRef: MatDialogRef<IGenerica>,
    @Inject(MAT_DIALOG_DATA) public data: IGenerica,
    private service: ModeloService,
    private service1: MarcaService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.consultarMarcas();
    this.configurarFormulario();
  }

  registerForm = this.fb.group({
    id: [0],
    nombreModelo: ['', Validators.required],
    marca: this.fb.group({
      id: [0],
      nombreMarca: [''],
    }),
  });

  hiddeMarca: boolean = false;

  async configurarFormulario() {
    if (this.dataService.id != 0) {
      this.autocompletar();
    }
    if (this.dataService.idMarca != 0) {
      this.hiddeMarca = true;
      let id = this.dataService.idMarca;

      setTimeout(() => {
        this.autocompletarMarca(id);
      }, 1000);
    }
  }

  autocompletar() {
    this.service.consultaPorId(this.dataService.id).subscribe((r) => {
      console.log(r);
      this.registerForm.patchValue({
        id: r.id,
        nombreModelo: r.nombreModelo,
        marca:({
          id: r.marca.id,
          nombreMarca: r.marca.nombreMarca,
        }),
      });
      console.log(this.registerForm.value)
    });
  }

  async autocompletarMarca(idMarca: number) {
    this.service1.consultaPorId(idMarca).subscribe((r) => {
      console.log(r);
      this.registerForm.patchValue({
        id: 0,
        nombreModelo: '',
        marca:({
          id: r.id,
          nombreMarca: r.nombreMarca,
        }),
      });
      console.log(this.registerForm.value)
    });
  }


  register() {
    this.service.alta(this.registerForm.value).subscribe((id) => {
      this.service.consultaPorId(id).subscribe((data) => {
        sweetalert.success();
        this.dataService.object = data;
        this.onNoClick();
      })
    });
  }

  consultarMarcas(): void {
    this.service1.consulta().subscribe((r: IGenerica[]) => {
      this.lista = r;
      this.filterItems = r;
    });
  }

  filtrar() {
    this.filterItems = this.lista?.filter((f) =>
      f.nombreMarca?.toLowerCase().trim().includes(this.filtro)
    );
  }

  marca!: IMarca;

  displayMarca(id: number) {
    this.registerForm.value.marca.id = id;
    if (!id) return '';

    let index = this.lista.findIndex((r) => r.id === id);
    console.log('index', index);

    this.marca = this.lista[index];

    return this.marca.nombreMarca;
  }

  abrirModal(id: number): void {
    this.dataService.id = id;
    const dialogRef = this.dialog.open(MarcaComponent, {
      width: '450px',
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (this.dataService.object != null) {
        this.lista = this.lista.filter((element) => element.id != id);
        this.lista.push(this.dataService.object);
        this.dataService.object = null;
      }
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}

export interface IGenerica extends IMarca, IModelo{}