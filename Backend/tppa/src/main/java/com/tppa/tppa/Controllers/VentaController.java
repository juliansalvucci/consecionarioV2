package com.tppa.tppa.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tppa.tppa.Models.Venta;
import com.tppa.tppa.Models.Requests.BusquedaAvanzadaRequest;
import com.tppa.tppa.Services.VentaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/venta")
public class VentaController  
{
    @Autowired
    VentaService service;
    

    @GetMapping()
    public ArrayList<Venta> obtener()
    {
        return service.obtener();
    }

    @PostMapping(path = "/jpql/obtenerPorRangoDeCostosYFechas")
    public List<Venta> obtenerPorRangoDeCostosYFechas(@RequestBody BusquedaAvanzadaRequest bar) 
    {
        return service.obtenerPorRangoDeCostosYFechas(bar);
    }

    @GetMapping(path = "/jpql/obtenerPorRangoDeCostos")
    public List<Venta> obtenerPorRangoDeCostos(Double montoInicial, Double montoFinal) 
    {
        return service.obtenerPorRangoDeCostos(montoInicial, montoFinal);
    }


    @GetMapping(path = "/jpql/getReporteCantidadYGananciaPorMarca")
    public List<Object> getReporteCantidadYGananciaPorMarca(String fechaDesde, String fechaHasta) 
    {        
        return service.getReporteCantidadYGananciaPorMarca(fechaDesde, fechaHasta);
    }

    @GetMapping(path = "/jpql/getReporteCantidadYGananciaPorMarcaYModelo")
    public List<Object> getReporteCantidadYGananciaPorMarcaYModelo(String fechaDesde, String fechaHasta) 
    {        
        return service.getReporteCantidadYGananciaPorMarcaYModelo(fechaDesde, fechaHasta);
    }

    @GetMapping(path = "/jpql/getReporteCantidadYGananciaPorEmpleado")
    public List<Object> getReporteCantidadYGananciaPorEmpleado(String fechaDesde, String fechaHasta) 
    {        
        return service.getReporteCantidadYGananciaPorEmpleado(fechaDesde, fechaHasta);
    }


    @GetMapping(path = "/jpql/getReporteVentasPorCategoria")
    public List<Object> getReporteVentasPorCategoria(String fechaDesde, String fechaHasta) 
    {        
        return service.getReporteVentasPorCategoria(fechaDesde, fechaHasta);
    }

    @GetMapping(path = "/jpql/getReporteDetalleVentasPorEmpleado")
    public List<Object> getReporteDetalleVentasPorEmpleado(String fechaDesde, String fechaHasta) 
    {        
        return service.getReporteDetalleVentasPorEmpleado(fechaDesde, fechaHasta);
    }


    @GetMapping(path = "/jpql/gananciaTotal")
    public Object obtenerGananciaTotal(String fechaDesde, String fechaHasta)
    {
        return service.obtenerGananciaTotal(fechaDesde, fechaHasta);
    }

    
    @PostMapping()
    public Venta guardar(@RequestBody Venta venta)
    {        
        return this.service.guardar(venta);
    }
 

    @GetMapping( path = "/{id}")
    public Optional<Venta> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }   
}

