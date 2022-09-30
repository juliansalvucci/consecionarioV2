import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { IPais } from 'src/interfaces/IPais';
import { DataService } from 'src/services/data.service';
import { PaisService } from 'src/services/pais.service';

@Component({
  selector: 'app-pais',
  templateUrl: './pais.component.html',
  styleUrls: ['./pais.component.css']
})
export class PaisComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<IPais>,
    @Inject(MAT_DIALOG_DATA) public data:IPais,
     private service: PaisService,
     private fb: FormBuilder, 
     private dataService: DataService,
     public dialog: MatDialog) 
     {
         this.configurarFormulario();
     }

  ngOnInit(): void {}

  registerForm = this.fb.group({
    id: [0],
    nombrePais: [""]
  });

  configurarFormulario()
  {
    if(this.dataService.id != 0)
    {
      this.autocompletar();
    }
  }

  autocompletar()
  {
    this.service.consultaPorId(this.dataService.id).subscribe((r) =>{
      console.log(r);
      this.registerForm.patchValue({
        id: r.id,
        nombrePais: r.nombrePais
      });
    });
  }

  register() 
  {
    if (this.registerForm.valid)
    {
      console.log(this.registerForm.value);
      this.service.alta(this.registerForm.value).subscribe(
        (data) => {
          this.dataService.object = data;
          console.log('Registro realizado con éxito');
          this.onNoClick();
        }
      );
    } 
    else
    {
      console.log(this.registerForm.value);
      console.log('modelo invalido');
      this.onNoClick();
    }
  }

  
  onNoClick(): void 
  {
    this.dialogRef.close();
  }
}
