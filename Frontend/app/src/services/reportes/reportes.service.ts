import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportesService {

  constructor(private http: HttpClient) { }

  consulta():Observable<any>
  {
    return this.http.get('http://localhost:8080/venta/jpql/getReporteCantidadYGananciaPorMarca?fechaDesde=2022-11-01T03%3A21%3A40.487Z&fechaHasta=2022-11-15T03%3A21%3A40.487Z')
  }
}
