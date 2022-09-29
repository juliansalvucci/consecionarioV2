import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_ROUTES } from 'src/routes/apiRoutes';

@Injectable({
  providedIn: 'root'
})
export class PaisService
{
  constructor(private http: HttpClient) { }

  alta(data:any):Observable<any>
  {
    return this.http.post(API_ROUTES.PAIS.ALTAPAIS, data);
  }

  baja(id:number):Observable<any>
  {
    return this.http.delete(API_ROUTES.PAIS.BAJAPAIS + id);
  }

  modificacion(data:any):Observable<any>
  {
    return this.http.post(API_ROUTES.PAIS.MODIFICACIONPAIS, data);
  }

  consulta():Observable<any>
  {
    return this.http.get(API_ROUTES.PAIS.CONSULTARPAISES);
  }

  consultaPorId(id:number):Observable<any>
  {
    return this.http.get(API_ROUTES.PAIS.CONSULTARPAIS + id);
  }
  
}
