import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IRango } from 'src/interfaces/IRango';
import { DataService } from 'src/services/data.service';
import { RangoService } from 'src/services/rango/rango.service';

@Component({
  selector: 'app-rango',
  templateUrl: './rango.component.html',
  styleUrls: ['./rango.component.css']
})
export class RangoComponent  {

  filterItems!: IGenerica[];
  lista!: IGenerica[];

  filtro: string = '';

  nombre!: string;

  constructor(
    public dialogRef: MatDialogRef<IGenerica>,
    @Inject(MAT_DIALOG_DATA) public data: IGenerica,
    private service: RangoService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.configurarFormulario();
  }

  registerForm = this.fb.group({
    id: [0],
    montoInicial: ['', Validators.required],
    montoFinal: ['', Validators.required],
    valor: ['', Validators.required],
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
        montoInicial: r.montoInicial,
        montoFinal: r.montoFinal,
        valor: r.valor
      });
    });
  }

  register() {
    console.log(this.registerForm.value);
    this.service.alta(this.registerForm.value).subscribe((data) => {
      this.dataService.object = data;
      console.log('Registro realizado con éxito');
      this.onNoClick();
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}

export interface IGenerica extends IRango{}