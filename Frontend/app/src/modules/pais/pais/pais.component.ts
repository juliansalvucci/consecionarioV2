import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {MatDialog,MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ICategoria } from 'src/interfaces/ICategoria';
import { IPais } from 'src/interfaces/IPais';
import { CategoriaService } from 'src/services/categoria/categoria.service';
import { DataService } from 'src/services/data.service';
import { PaisService } from 'src/services/pais/pais.service';

@Component({
  selector: 'app-pais',
  templateUrl: './pais.component.html',
  styleUrls: ['./pais.component.css'],
})
export class PaisComponent implements OnInit {
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

  ngOnInit(): void {}

  registerForm = this.fb.group({
    id: [0],
    nombrePais: ['', Validators.required],
    idCategoria: ['', Validators.required],
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
        idCategoria: r.categoria?.id,
      });
    });
  }

  setFormValues() {
    this.registerForm.value.categoria.id = this.categoria.id;
    this.registerForm.value.categoria.nombreCategoria = this.categoria.nombreCategoria;
  }

  register() {
    try {
      
      this.setFormValues();

      this.service.alta(this.registerForm.value).subscribe((data) => {
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

  onNoClick(): void {
    this.dialogRef.close();
  }

  consultarCategorias(): void {
    try {
      this.service1.consulta().subscribe((r: IGenerica[]) => {
        console.log(r);
        this.lista = r;
        this.filterItems = r;
      });
    } catch (e) {
      console.log(e);
    }
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
}

export interface IGenerica extends IPais, ICategoria{}