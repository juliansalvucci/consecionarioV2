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
     private paisService: PaisService,
     private fb: FormBuilder, 
     private dataService: DataService,
     public dialog: MatDialog) 
     {
         this.autocompletar();
     }

  ngOnInit(): void {
  }

  registerForm = this.fb.group({
    id: [0],
    nombrePais: [""]
  });

  autocompletar()
  {
    this.paisService.consultarPais(this.dataService.id).subscribe((r) =>{
      console.log(r);
      this.registerForm.patchValue({
        id: r.id,
        nombrePais: r.nombrePais
      });
    });
  }

  register() 
  {
    
    console.log('registrar');

    if (this.registerForm.valid)
    {
      console.log('TROLO',this.registerForm.value);
      this.paisService.modificacionPais(this.registerForm.value).subscribe(
        (data) => {
          console.log(data);
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
