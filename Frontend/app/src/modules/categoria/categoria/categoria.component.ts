import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ICategoria } from 'src/interfaces/ICategoria';
import { CategoriaService } from 'src/services/categoria/categoria.service';
import { DataService } from 'src/services/data.service';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ICategoria>,
    @Inject(MAT_DIALOG_DATA) public data: ICategoria,
    private service: CategoriaService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.configurarFormulario();
  }

  ngOnInit(): void {}

  registerForm = this.fb.group({
    id: [0],
    nombreCategoria: ['', Validators.required],
    porcentaje: ['', Validators.required],
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
        nombreCategoria: r.nombreCategoria,
        porcentaje: r.porcentaje
      });
    });
  }

  register() {
    try {
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

}
