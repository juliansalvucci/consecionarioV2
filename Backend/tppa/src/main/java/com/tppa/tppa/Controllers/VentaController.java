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
import com.tppa.tppa.Models.Requests.BusquedaAvanzadaV2Request;
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
        try
        {
            return service.obtener();
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @PostMapping()
    public Boolean guardar(@RequestBody Venta venta)
    {       
        try
        {
            service.guardar(venta);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
 

    @GetMapping( path = "/{id}")
    public Optional<Venta> obtenerPorId(@PathVariable("id") Long id)
    {
        try
        {
            return service.obtenerPorId(id);
        }
        catch(Exception ex)
        {
            return null;
        } 
    } 

    @PostMapping(path = "criteria/busquedaAvanzada")
    public List<Venta> busquedaAvanzada(@RequestBody BusquedaAvanzadaV2Request bar)
    {
        try
        {
            return service.busquedaAvanzada(bar);
        }
        catch(Exception ex)
        {
             return null;
        }
    }

    @PostMapping(path = "/jpql/obtenerPorRangoDeCostosYFechas")
    public List<Venta> obtenerPorRangoDeCostosYFechas(@RequestBody BusquedaAvanzadaRequest bar) 
    {
        try
        {
            return service.obtenerPorRangoDeCostosYFechas(bar);
        }
        catch(Exception ex)
        {
             return null;
        }
    }

    @GetMapping(path = "/jpql/obtenerPorRangoDeCostos")
    public List<Venta> obtenerPorRangoDeCostos(Double montoInicial, Double montoFinal) 
    {
        try
        {
            return service.obtenerPorRangoDeCostos(montoInicial, montoFinal);
        }
        catch(Exception ex)
        {
             return null;
        }
    }


    @GetMapping(path = "/jpql/getReporteCantidadYGananciaPorMarca")
    public List<Object> getReporteCantidadYGananciaPorMarca(String fechaDesde, String fechaHasta) 
    {        
        try
        {
            return service.getReporteCantidadYGananciaPorMarca(fechaDesde, fechaHasta);
        }
        catch(Exception ex)
        {
             return null;
        }
    }

    @GetMapping(path = "/jpql/getReporteCantidadYGananciaPorMarcaYModelo")
    public List<Object> getReporteCantidadYGananciaPorMarcaYModelo(String fechaDesde, String fechaHasta) 
    {     
        try
        {
            return service.getReporteCantidadYGananciaPorMarcaYModelo(fechaDesde, fechaHasta);
        }
        catch(Exception ex)
        {
             return null;
        }   
    }

    @GetMapping(path = "/jpql/getReporteCantidadYGananciaPorEmpleado")
    public List<Object> getReporteCantidadYGananciaPorEmpleado(String fechaDesde, String fechaHasta) 
    {    
        try
        {
            return service.getReporteCantidadYGananciaPorEmpleado(fechaDesde, fechaHasta);
        }
        catch(Exception ex)
        {
             return null;
        }    
    }


    @GetMapping(path = "/jpql/getReporteVentasPorCategoria")
    public List<Object> getReporteVentasPorCategoria(String fechaDesde, String fechaHasta) 
    {    
        try
        {
            return service.getReporteVentasPorCategoria(fechaDesde, fechaHasta);
        }
        catch(Exception ex)
        {
             return null;
        }    
    }

    @GetMapping(path = "/jpql/getReporteDetalleVentasPorEmpleado")
    public List<Object> getReporteDetalleVentasPorEmpleado(String fechaDesde, String fechaHasta) 
    {      
        try
        {
            return service.getReporteDetalleVentasPorEmpleado(fechaDesde, fechaHasta);
        }
        catch(Exception ex)
        {
             return null;
        }  
    }


    @GetMapping(path = "/jpql/gananciaTotal")
    public Object obtenerGananciaTotal(String fechaDesde, String fechaHasta)
    {
        try
        {
            return service.obtenerGananciaTotal(fechaDesde, fechaHasta);
        }
        catch(Exception ex)
        {
             return null;
        }
    }    
}

