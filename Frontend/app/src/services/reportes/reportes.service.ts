import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportesService {

  constructor(private http: HttpClient) { }

  consulta(fechaDesde:string, fechaHasta: string):Observable<any>
  {
    return this.http.get(`http://localhost:8080/venta/jpql/getReporteCantidadYGananciaPorMarca?fechaDesde=${fechaDesde}&fechaHasta=${fechaHasta}`)
  }

  consulta2(fechaDesde:string, fechaHasta: string):Observable<any>
  {
    return this.http.get(`http://localhost:8080/venta/jpql/getReporteCantidadYGananciaPorEmpleado?fechaDesde=${fechaDesde}&fechaHasta=${fechaHasta}`)
  }

  consulta3(fechaDesde:string,fechaHasta:string):Observable<any>
  {
    return this.http.get(`http://localhost:8080/venta/jpql/getReporteCantidadYGananciaPorMarcaYModelo?fechaDesde=${fechaDesde}&fechaHasta=${fechaHasta}`)
  }

  consulta4(fechaDesde:string,fechaHasta:string):Observable<any>
  {
    return this.http.get(`http://localhost:8080/venta/jpql/getReporteVentasPorCategoria?fechaDesde=${fechaDesde}&fechaHasta=${fechaHasta}`)
    
  }

  consulta5(fechaDesde:string,fechaHasta:string):Observable<any>
  {
    return this.http.get(`http://localhost:8080/venta/jpql/getReporteDetalleVentasPorEmpleado?fechaDesde=${fechaDesde}&fechaHasta=${fechaHasta}`)
    
  }

}
