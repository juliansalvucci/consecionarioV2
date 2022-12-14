import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IMarca } from 'src/interfaces/IMarca';
import { DataService } from 'src/services/data.service';
import { MarcaService } from 'src/services/marca/marca.service';
import { sweetalert } from 'src/utils/utils';

@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css'],
})
export class MarcaComponent {
  filterItems!: IGenerica[];
  lista!: IGenerica[];

  filtro: string = '';

  nombre!: string;

  constructor(
    public dialogRef: MatDialogRef<IGenerica>,
    @Inject(MAT_DIALOG_DATA) public data: IGenerica,
    private service: MarcaService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.configurarFormulario();
  }

  registerForm = this.fb.group({
    id: [0],
    nombreMarca: ['', Validators.required],
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
        nombreMarca: r.nombreMarca,
      });
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

  onNoClick(): void {
    this.dialogRef.close();
  }
}

export interface IGenerica extends IMarca{}