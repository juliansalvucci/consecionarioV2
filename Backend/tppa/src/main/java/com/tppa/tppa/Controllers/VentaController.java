package com.tppa.tppa.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Venta> cr = cb.createQuery(Venta.class);
        Root<Venta> root = cr.from(Venta.class);

        cr.select(root).where(cb.between(root.get("costo"), montoInicial, montoFinal));
        cr.select(root).where(cb.between(root.get("fechaVenta"), fechaDesde, fechaHasta));

        TypedQuery<Venta> query = em.createQuery(cr);
        List<Venta> results = query.getResultList();

        em.close();


        return results;
    }

    @GetMapping(path = "/criteria/obtenerPorRangoDeCostos")
    public List<Venta> obtenerPorRangoDeCostos(Double montoInicial, Double montoFinal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Venta> cr = cb.createQuery(Venta.class);
        Root<Venta> root = cr.from(Venta.class);

        cr.select(root).where(cb.between(root.get("costo"), montoInicial, montoFinal));

        TypedQuery<Venta> query = em.createQuery(cr);
        List<Venta> results = query.getResultList();

        em.close();


        return results;
    }

    @GetMapping(path = "/criteria/obtenerPorRangoDeFechas")
    public List<Venta> obtenerPorRangoDeFechas(String fechaDesde,String fechaHasta) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Venta> cr = cb.createQuery(Venta.class);
        Root<Venta> root = cr.from(Venta.class);

        cr.select(root).where(cb.between(root.get("fechaVenta"), fechaDesde, fechaHasta));

        TypedQuery<Venta> query = em.createQuery(cr);
        List<Venta> results = query.getResultList();

        em.close();


        return results;
    }

    @GetMapping(path = "/criteria/gananciaTotal")
    public List<Double> obtenerGananciaTotal() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> cr = cb.createQuery(Double.class);
        Root<Venta> root = cr.from(Venta.class);
        
        cr.select(cb.sum(root.get("costo")));

        TypedQuery<Double> query = em.createQuery(cr);
        List<Double> results = query.getResultList();

        em.close();

        return results;
    }

    @GetMapping(path = "/criteria/gananciaTotalPorMarca")
    public List<Double> obtenerGananciaTotalPorMarca() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> cr = cb.createQuery(Double.class);
        Root<Venta> root = cr.from(Venta.class);
        
        cr.select(cb.sum(root.get("costo")))
          .groupBy(root.get("auto").get("modelo").get("marca").get("nombreMarca"));

        TypedQuery<Double> query = em.createQuery(cr);
        List<Double> results = query.getResultList();

        em.close();

        return results;
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

