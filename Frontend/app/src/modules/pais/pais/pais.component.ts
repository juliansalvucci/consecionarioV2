import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {MatDialog,MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ICategoria } from 'src/interfaces/ICategoria';
import { IPais } from 'src/interfaces/IPais';
import { CategoriaComponent } from 'src/modules/categoria/categoria/categoria.component';
import { CategoriaService } from 'src/services/categoria/categoria.service';
import { DataService } from 'src/services/data.service';
import { PaisService } from 'src/services/pais/pais.service';
import { sweetalert } from 'src/utils/utils';

@Component({
  selector: 'app-pais',
  templateUrl: './pais.component.html',
  styleUrls: ['./pais.component.css'],
})
export class PaisComponent {
  filterItems!: IGenerica[];
  lista!: IGenerica[];

  filtro: string = '';

  constructor(
    public dialogRef: MatDialogRef<IPais>,
    @Inject(MAT_DIALOG_DATA) public data: IPais,
    private service: PaisService,
    private service1: CategoriaService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.consultarCategorias();
    this.configurarFormulario();
  }

  registerForm = this.fb.group({
    id: [0],
    nombrePais: ['', Validators.required],
    categoria: this.fb.group({
      id: [0],
      nombreCategoria: [''],
      porcentaje: [''],
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
        nombrePais: r.nombrePais,
        categoria: {
          id: r.categoria.id,
          nombreCategoria: r.nombreCategoria,
          porcentaje: r.porcentaje,
        },
      });
    });
  }


  register() {
    this.service.alta(this.registerForm.value).subscribe((id) => {
      this.service.consultaPorId(id).subscribe((data) => {
        this.dataService.object = data;
        sweetalert.success();
        this.onNoClick();
      })
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  consultarCategorias(): void {
    this.service1.consulta().subscribe((r: IGenerica[]) => {
      console.log(r);
      this.lista = r;
      this.filterItems = r;
    });
  }

  filtrar() {
    this.filterItems = this.lista?.filter((f) =>
      f.nombreCategoria?.toLowerCase().trim().includes(this.filtro)
    );
  }

  categoria!: ICategoria;

  displayCategoria(id: number) {
    console.log(id);

    this.registerForm.value.categoria.id = id; //Seteo el id en el formgroup
    if (!id) return '';

    let index = this.lista.findIndex((r) => r.id === id);
    console.log('index', index);

    this.categoria = this.lista[index];

    return this.categoria.nombreCategoria;
  }

  abrirModal(id: number): void {
    this.dataService.id = id;
    const dialogRef = this.dialog.open(CategoriaComponent, {
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
}

export interface IGenerica extends IPais, ICategoria{}