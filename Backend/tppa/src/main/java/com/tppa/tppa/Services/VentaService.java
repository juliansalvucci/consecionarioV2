package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Models.Venta;
import com.tppa.tppa.Models.Requests.BusquedaAvanzadaRequest;
import com.tppa.tppa.Repositories.AutoRepositories.AutoCustomRepository;
import com.tppa.tppa.Repositories.AutoRepositories.AutoRepository;
import com.tppa.tppa.Repositories.VentaRepositories.VentaCustomRepository;
import com.tppa.tppa.Repositories.VentaRepositories.VentaRepository;

@Service
public class VentaService
{
    @Autowired
    VentaRepository repository;
    @Autowired
    VentaCustomRepository customRepository;
    @Autowired AutoRepository autoRepository;
    @Autowired AutoCustomRepository autoCustomRepository;
    
    public ArrayList<Venta> obtener()
    {
        return (ArrayList<Venta>) repository.findAll();
    }


    public Venta guardar(Venta venta)
    {

        Auto auto = autoCustomRepository.obtenerPorIdAuto(venta.getAuto().getId());

        auto.setVendido(true);
        
        Double precio = auto.getPrecio();
        Double costo = auto.getCosto();
        Double ganancia = auto.getGanancia();
        int porcentaje = auto.getPais().getCategoria().getPorcentaje();

        autoRepository.save(auto);

        venta.setPrecio(precio);
        venta.setCosto(costo);
        venta.setGanancia(ganancia);
        venta.setPorcentaje(porcentaje);
        
        
        return repository.save(venta);
    }

    public Optional<Venta> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }  

    public List<Venta> obtenerPorRangoDeCostosYFechas(@RequestBody BusquedaAvanzadaRequest bar) 
    {
        return customRepository.obtenerPorRangoDeCostosYFechas(bar);
    }

    public List<Venta> obtenerPorRangoDeCostos(Double montoInicial, Double montoFinal)
    {
        return customRepository.obtenerPorRangoDeCostos(montoInicial, montoFinal);
    }

    public List<Object> getReporteCantidadYGananciaPorMarca(String fechaDesde, String fechaHasta) 
    {        
        return customRepository.getReporteCantidadYGananciaPorMarca(fechaDesde, fechaHasta);
    }

    public List<Object> getReporteCantidadYGananciaPorMarcaYModelo(String fechaDesde, String fechaHasta) 
    {        
        return customRepository.getReporteCantidadYGananciaPorMarcaYModelo(fechaDesde, fechaHasta);
    }

    public List<Object> getReporteCantidadYGananciaPorEmpleado(String fechaDesde, String fechaHasta) 
    {        
        return customRepository.getReporteCantidadYGananciaPorEmpleado(fechaDesde, fechaHasta);
    }

    public List<Object> getReporteVentasPorCategoria(String fechaDesde, String fechaHasta) 
    {        
        return customRepository.getReporteVentasPorCategoria(fechaDesde, fechaHasta);
    }

    public List<Object> getReporteDetalleVentasPorEmpleado(String fechaDesde, String fechaHasta) 
    {        
        return customRepository.getReporteDetalleVentasPorEmpleado(fechaDesde, fechaHasta);
    }

    public Object obtenerGananciaTotal(String fechaDesde, String fechaHasta) 
    {
        return customRepository.obtenerGananciaTotal(fechaDesde, fechaHasta);
    }
}
