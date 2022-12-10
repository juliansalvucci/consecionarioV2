package com.tppa.tppa.Repositories.VentaRepositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Venta;
import com.tppa.tppa.Models.Requests.BusquedaAvanzadaRequest;
import com.tppa.tppa.Models.Requests.BusquedaAvanzadaV2Request;

@Repository
public interface IVentaCustomRepository 
{
    public List<Venta> busquedaAvanzada(BusquedaAvanzadaV2Request bar);

    public List<Venta> obtenerPorRangoDeCostosYFechas(BusquedaAvanzadaRequest bar);

    public List<Venta> obtenerPorRangoDeCostos(Double montoInicial, Double montoFinal);

    public List<Object> getReporteCantidadYGananciaPorMarca(String fechaDesde, String fechaHasta);

    public List<Object> getReporteCantidadYGananciaPorMarcaYModelo(String fechaDesde, String fechaHasta);

    public List<Object> getReporteVentasPorCategoria(String fechaDesde, String fechaHasta);

    public List<Object> getReporteDetalleVentasPorEmpleado(String fechaDesde, String fechaHasta);

    public Object obtenerGananciaTotal(String fechaDesde, String fechaHasta);
}
