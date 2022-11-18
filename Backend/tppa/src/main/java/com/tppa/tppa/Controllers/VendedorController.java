package com.tppa.tppa.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tppa.tppa.Models.Usuario;
import com.tppa.tppa.Models.Vendedor;
import com.tppa.tppa.Services.EmpleadoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/Vendedor")
public class VendedorController 
{
    @Autowired
    EmpleadoService service;
    EntityManager em;

    public VendedorController(EntityManager em){
        this.em = em;
    }


    @GetMapping()
    public ArrayList<Vendedor> obtener()
    {
        return service.obtener();
    }

    @PostMapping()
    public Vendedor guardar(@RequestBody Vendedor vendedor)
    {
        return this.service.guardar(vendedor);
    }

    @GetMapping( path = "/{id}")
    public Optional<Vendedor> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }


    @GetMapping(path="/login")
    public Object ObtenerPorUsuario(Usuario usuario)
    {
        String CONSULTA = "SELECT vendedor FROM Vendedor vendedor WHERE vendedor.nombreUsuario = :nombreUsuario AND vendedor.contraseña = :contraseña";
        var registro = em.createQuery(CONSULTA)
        .setParameter("nombreUsuario", usuario.getNombreUsuario())
        .setParameter("contraseña", usuario.getContraseña())
        .getSingleResult();

        em.close();
        return registro;
    }

    @DeleteMapping( path = "/{id}")
    public Boolean eliminarPorId(@PathVariable("id") Long id)
    {
        return this.service.eliminar(id);    
    }    
}