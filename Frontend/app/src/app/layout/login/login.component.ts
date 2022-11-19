import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IUsuario } from 'src/interfaces/IUsuario';
import { AuthService } from 'src/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(
    private router: Router,
    private authService: AuthService,
    private fb: FormBuilder,
  ) {}

  registerForm = this.fb.group({
    nombreUsuario: ['', Validators.required],
    contraseña: ['', Validators.required],
  });

  ngOnInit(): void {}

  public STR_SYSTEM = 'listaAutos';


  login() {
    try {
      console.log(this.registerForm.value);
      this.authService.login(this.registerForm.value).subscribe((data:IUsuario) => {
        console.log('Registro realizado con éxito');
        localStorage.setItem("user",JSON.stringify(data))
        this.router.navigateByUrl('system');
        console.log(data)
      });
    } catch (e) {
      console.log(this.registerForm.value);
      console.log('modelo invalido');
    }
  }
}
