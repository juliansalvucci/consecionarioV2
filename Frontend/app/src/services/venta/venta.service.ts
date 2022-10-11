import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_ROUTES } from 'src/routes/apiRoutes';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  constructor(private http: HttpClient) { }

  URL = API_ROUTES.VENTA

  alta(data:any):Observable<any>
  {
    return this.http.post(this.URL.ALTA, data);
  }

  consulta():Observable<any>
  {
    return this.http.get(this.URL.CONSULTA);
  }

  consultaPorId(id:number):Observable<any>
  {
    return this.http.get(this.URL.CONSULTAPORID + id);
  }
}
