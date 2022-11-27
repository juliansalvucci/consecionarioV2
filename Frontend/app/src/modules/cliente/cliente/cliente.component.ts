import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {MatDialog,MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import { IPais } from 'src/interfaces/IPais';
import { ClienteService } from 'src/services/cliente/cliente.service';
import { DataService } from 'src/services/data.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css'],
})
export class ClienteComponent {
  constructor(
    public dialogRef: MatDialogRef<IPais>,
    @Inject(MAT_DIALOG_DATA) public data: IPais,
    private service: ClienteService,
    private fb: FormBuilder,
    private dataService: DataService,
    public dialog: MatDialog
  ) {
    this.configurarFormulario();
  }


  registerForm = this.fb.group({
    id: [0],
    nombre: ['', Validators.required],
    apellido: ['', Validators.required],
    documento: ['', Validators.required],
  });

  configurarFormulario() {
    if (this.dataService.documento != '') {
      this.autocompletar();
    }
  }

  autocompletar() {
    this.service
      .consultaPorDoumento(this.dataService.documento)
      .subscribe((r) => {
        console.log(r);
        this.registerForm.patchValue({
          id: r.id,
          nombre: r.nombre,
          apellido: r.apellido,
          documento: r.documento,
        });
      });
  }

  register() {
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
