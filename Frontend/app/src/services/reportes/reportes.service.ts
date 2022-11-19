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
    return this.http.get('http://localhost:8080/venta/jpql/getReporteCantidadYGananciaPorMarca?fechaDesde=2022-11-19T05%3A41%3A37.836Z&fechaHasta=2022-12-29T05%3A41%3A37.836Z')
  }

  consulta2():Observable<any>
  {
    return this.http.get('http://localhost:8080/venta/jpql/getReporteCantidadYGananciaPorEmpleado?fechaDesde=2022-10-06T19%3A58%3A17.355Z&fechaHasta=2022-11-26T19%3A58%3A17.355Z')
  }

  consulta3():Observable<any>
  {
    return this.http.get('http://localhost:8080/venta/jpql/getReporteCantidadYGananciaPorMarcaYModelo?fechaDesde=2022-10-06T19%3A58%3A17.355Z&fechaHasta=2022-11-26T19%3A58%3A17.355Z')
  }

  consulta4():Observable<any>
  {
    return this.http.get('http://localhost:8080/venta/jpql/getReporteVentasPorCategoria?fechaDesde=2022-10-06T19%3A58%3A17.355Z&fechaHasta=2022-11-26T19%3A58%3A17.355Z')
    
  }

}
