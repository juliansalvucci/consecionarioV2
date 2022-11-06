package com.tppa.tppa.Controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tppa.tppa.Models.Pais;
import com.tppa.tppa.Services.PaisService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/pais")
public class PaisController 
{
    @Autowired
    PaisService service;
    EntityManager em;

    public PaisController(EntityManager em){
        this.em = em;
    }
    
    @GetMapping()
    public List<Pais> obtener()
    {
        return service.obtener();
    }
    

    @GetMapping(path = "/criteria")
    public List<Pais> obtenerPorCriteria()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pais> cr = cb.createQuery(Pais.class);
        Root<Pais> root = cr.from(Pais.class);
        //cr.select(root);
        cr.select(root).where(cb.like(root.get("nombrePais"), "Argentina"));

        TypedQuery<Pais> query = em.createQuery(cr);
        List<Pais> results = query.getResultList();

        return results;
    }
    
    
    @PostMapping()
    public Pais guardar(@RequestBody Pais pais)
    {
        return this.service.guardar(pais);
    }

    @GetMapping( path = "/{id}")
    public Optional<Pais> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public Boolean eliminarPorId(@PathVariable("id") Long id)
    {
        return this.service.eliminar(id);    
    }
    
}