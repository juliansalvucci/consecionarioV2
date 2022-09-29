import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_ROUTES } from 'src/routes/apiRoutes';
import { environment as ENV } from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PaisService
{
  constructor(private http: HttpClient) { }

  consultarPaises():Observable<any>
  {
    return this.http.get(API_ROUTES.PAIS.CONSULTARPAISES);
  }

  consultarPais(id:number):Observable<any>
  {
    return this.http.get(API_ROUTES.PAIS.CONSULTARPAIS + id);
  }

  altaPais(data:any):Observable<any>
  {
    return this.http.post(API_ROUTES.PAIS.ALTAPAIS, data);
  }

  modificacionPais(data:any):Observable<any>
  {
    return this.http.put(API_ROUTES.PAIS.MODIFICACIONPAIS, data);
  }

  bajaCliente(id:number):Observable<any>
  {
    return this.http.delete(API_ROUTES.PAIS.BAJAPAIS + id);
  }
}
