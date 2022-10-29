import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IMarca } from 'src/interfaces/IMarca';
import { IPais } from 'src/interfaces/IPais';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { PaisService } from 'src/services/pais/pais.service';

@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css']
})
export class MarcaComponent implements OnInit {

  filterItems!:IGenerica[]
  lista!:IGenerica[]

  filtro: string = '';

  nombre!: string;

  constructor(
    public dialogRef: MatDialogRef<IGenerica>,
    @Inject(MAT_DIALOG_DATA) public data: IGenerica,
    private service: MarcaService,
    private service1: PaisService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.consultarPaises();
    this.configurarFormulario();
  }

  ngOnInit(): void {}

  registerForm = this.fb.group({
    id: [0],
    nombreMarca: ['',Validators.required],
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
        nombreMarca: r.nombreMarca,
        idPais: r.pais.id,
        pais: ({
          id: r.id,
          nombrePais: r.nombrePais
        })
      });
    });
  }




  register() {
    try {
      console.log(this.registerForm.value)
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

  consultarPaises(){
    try  {
      this.service1.consulta().subscribe((r: IGenerica[]) => {
        console.log('Países',r);
        this.lista = r
        this.filterItems = this.lista
      });
    } catch (e) {
      console.log(e);
    }
  }

  filtrar() {
    this.filterItems = this.lista?.filter((f) => f.nombrePais?.toLowerCase().trim().includes(this.filtro));
  }

  pais!: IPais

  displayPaises(id: number) {
    this.registerForm.value.pais.id = id;
    if (!id) return '';

    let index = this.lista.findIndex((r) => r.id === id);
    console.log('index', index);
  
    this.pais = this.lista[index];

    return this.pais.nombrePais
  }
  

  onNoClick(): void {
    this.dialogRef.close();
  }

}

export interface IGenerica extends IMarca, IPais{}