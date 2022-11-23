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

import com.tppa.tppa.Models.BusquedaAvanzadaRequest;
import com.tppa.tppa.Models.GananaciaPorMarca;
import com.tppa.tppa.Models.GananciaPorCategoria;
import com.tppa.tppa.Models.GananciaPorEmpleado;
import com.tppa.tppa.Models.GananciaPorModeloYMarca;
import com.tppa.tppa.Models.GananciaYCantidadEnPeriodo;
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

    @PostMapping(path = "/jpql/obtenerPorRangoDeCostosYFechas")
    public List<Venta> obtenerPorRangoDeCostosYFechas(@RequestBody BusquedaAvanzadaRequest bar) 
    {
        String CONSULTA = "SELECT venta FROM Venta venta WHERE venta.costo > :montoInicial AND venta.costo < :montoFinal AND  venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta";
        @SuppressWarnings("unchecked")
        List<Venta> registros = em.createQuery(CONSULTA)
        .setParameter("montoInicial", bar.getMontoInicial())
        .setParameter("montoFinal", bar.getMontoFinal())
        .setParameter("fechaDesde", bar.getFechaDesde())
        .setParameter("fechaHasta", bar.getFechaHasta())
        .getResultList();

        em.close();
        return registros;
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
            GananaciaPorMarca gpm = new GananaciaPorMarca();

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

    @GetMapping(path = "/jpql/getReporteCantidadYGananciaPorMarcaYModelo")
    public List<Object> getReporteCantidadYGananciaPorMarcaYModelo(String fechaDesde, String fechaHasta) {        
        String CONSULTA = "SELECT COUNT(venta) as cantidadVentas, SUM(venta.costo) as costo, venta.auto.modelo.marca.nombreMarca as marca, venta.auto.modelo.nombreModelo as modelo FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta GROUP BY modelo,marca";
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
            var modelo = obj[3].toString();


            gpm.setCantidadVentas(cantidad);
            gpm.setCosto(costo);
            gpm.setMarca(marca);
            gpm.setModelo(modelo);
            
            test.add(gpm);
        }

        em.close();
        return test;
    }

    @GetMapping(path = "/jpql/getReporteCantidadYGananciaPorEmpleado")
    public List<Object> getReporteCantidadYGananciaPorEmpleado(String fechaDesde, String fechaHasta) {        
        String CONSULTA = "SELECT COUNT(venta) as cantidadVentas, SUM(venta.costo) as costo, venta.empleado.nombre as empleado FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta GROUP BY empleado";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta)
        .getResultList();

        List<Object> test = new ArrayList<>();

        for(int i = 0; i < registros.size(); i++ ){
            Object[] obj = (Object[])registros.get(i);
            GananciaPorEmpleado gpm = new GananciaPorEmpleado();

            var cantidad = obj[0].toString();
            var costo = obj[1].toString();
            var empleado = obj[2].toString();


            gpm.setCantidadVentas(cantidad);
            gpm.setCosto(costo);
            gpm.setEmpleado(empleado);
            
            test.add(gpm);
        }

        em.close();
        return test;
    }


    @GetMapping(path = "/jpql/getReporteVentasPorCategoria")
    public List<Object> getReporteVentasPorCategoria(String fechaDesde, String fechaHasta) {        
        String CONSULTA = "SELECT COUNT(venta) as cantidadVentas, SUM(venta.costo) as costo, venta.auto.pais.categoria.nombreCategoria as categoria FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta GROUP BY categoria";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta)
        .getResultList();

        List<Object> test = new ArrayList<>();

        for(int i = 0; i < registros.size(); i++ ){
            Object[] obj = (Object[])registros.get(i);
            GananciaPorCategoria gpc = new GananciaPorCategoria();

            var cantidad = obj[0].toString();
            var costo = obj[1].toString();
            var categoria = obj[2].toString();


            gpc.setCantidadVentas(cantidad);
            gpc.setCosto(costo);
            gpc.setCategoria(categoria);
            
            test.add(gpc);
        }

        em.close();
        return test;
    }



    @GetMapping(path = "/jpql/gananciaTotal")
    public Object obtenerGananciaTotal(String fechaDesde, String fechaHasta) {
        String CONSULTA = "SELECT COUNT(venta), SUM(venta.costo) as costo FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta).getSingleResult();

        Object[] obj = (Object[])registros;
        GananciaYCantidadEnPeriodo gpc = new GananciaYCantidadEnPeriodo();

        if(obj[0]!= null && obj[1] != null){   //No se itera porque se devuelve un solo registro.
            var cantidad = obj[0].toString();
            var total = obj[1].toString();

            gpc.setCantidadVentas(cantidad);
            gpc.setTotal(total);
        }else{
            gpc = null; //Sino se encuentran resultado, devolver un objeto nulo.
        }
        
        em.close();
        return gpc;
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

