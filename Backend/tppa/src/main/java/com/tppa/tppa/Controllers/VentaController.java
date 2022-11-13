package com.tppa.tppa.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tppa.tppa.Models.GananciaPorModeloYMarca;
import com.tppa.tppa.Models.Venta;
import com.tppa.tppa.Services.VentaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/venta")
public class VentaController  
{
    @Autowired
    VentaService service;
    EntityManager em;

    public VentaController(EntityManager em){
        this.em = em;
    }

    @GetMapping()
    public ArrayList<Venta> obtener()
    {
        return service.obtener();
    }

    @GetMapping(path = "/criteria/obtenerPorRangoDeCostosYFechas")
    public List<Venta> obtenerPorRangoDeCostosYFechas(Double montoInicial, Double montoFinal, String fechaDesde,String fechaHasta) {
        return null;
    }

    @GetMapping(path = "/jpql/obtenerPorRangoDeCostos")
    public List<Venta> obtenerPorRangoDeCostos(Double montoInicial, Double montoFinal) {
        String CONSULTA = "SELECT venta FROM Venta venta WHERE venta.costo > :montoInicial AND venta.costo < :montoFinal ";
        @SuppressWarnings("unchecked")
        List<Venta> registros = em.createQuery(CONSULTA)
        .setParameter("montoInicial", montoInicial)
        .setParameter("montoFinal", montoFinal)
        .getResultList();

        em.close();
        return registros;
    }


    @GetMapping(path = "/jpql/getReporteCantidadYGananciaPorMarca")
    public List<Object> getReporteCantidadYGananciaPorMarca(String fechaDesde, String fechaHasta) {        
        String CONSULTA = "SELECT COUNT(venta) as cantidadVentas, SUM(venta.costo) as costo, venta.auto.modelo.marca.nombreMarca as marca FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta GROUP BY marca";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta)
        .getResultList();

        List<Object> test = new ArrayList<>();

        for(int i = 0; i < registros.size(); i++ ){
            Object[] obj = (Object[])registros.get(i);
            GananciaPorModeloYMarca gpm = new GananciaPorModeloYMarca();

            var cantidad = obj[0].toString();
            var costo = obj[1].toString();
            var marca = obj[2].toString();


            gpm.setCantidadVentas(cantidad);
            gpm.setCosto(costo);
            gpm.setMarca(marca);
            
            test.add(gpm);
        }

        em.close();
        return test;
    }



    @GetMapping(path = "/jpql/gananciaTotal")
    public Double obtenerGananciaTotal(String fechaDesde, String fechaHasta) {
        String CONSULTA = "SELECT SUM(venta.costo) as costo FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta";
        @SuppressWarnings("unchecked")
        List<Double> registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta).getResultList();

        em.close();
        return registros.get(0);
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

