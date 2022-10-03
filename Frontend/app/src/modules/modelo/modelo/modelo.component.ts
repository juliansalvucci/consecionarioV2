import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IMarca } from 'src/interfaces/IMarca';
import { IModelo } from 'src/interfaces/IModelo';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { ModeloService } from 'src/services/modelo/modelo.service';

@Component({
  selector: 'app-modelo',
  templateUrl: './modelo.component.html',
  styleUrls: ['./modelo.component.css']
})
export class ModeloComponent implements OnInit {

  filterItems!:IGenerica[]
  lista!:IGenerica[]

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

  ngOnInit(): void {}

  registerForm = this.fb.group({
    id: [0],
    nombreModelo: ['',Validators.required],
    idMarca: ['',Validators.required],
    marca: this.fb.group({
       id: []
    })
    
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
        nombreModelo: r.nombreModelo,
        idMarca: r.idMarca,
        marca: ({
          id: r.id
        }) 
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

  consultarMarcas(): void {
    try {
      this.service1.consulta().subscribe((r: IGenerica[]) => {
        console.log(r);
        this.lista = r
        this.filterItems = r
      });
    } catch (e) {
      console.log(e);
    }
  }

  filtrar() {
    this.filterItems = this.lista?.filter((f) => f.nombreMarca?.toLowerCase().trim().includes(this.filtro));
  }


  displayMarca(id: number) {
    console.log(id)
    this.registerForm.value.marca.id = id;
    if (!id) return '';

    let index = this.lista.findIndex((r) => r.id === id);
    console.log('index', index);
    return this.lista[index].nombreMarca;
  }
  

  onNoClick(): void {
    this.dialogRef.close();
  }
}

export interface IGenerica extends IMarca, IModelo{}