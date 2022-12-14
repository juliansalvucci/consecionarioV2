package com.tppa.tppa.Controllers;

import java.util.ArrayList;
import java.util.Optional;

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
    
    @GetMapping()
    public ArrayList<Vendedor> obtener()
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
    public Long guardar(@RequestBody Vendedor vendedor)
    {
        try
        {
            return service.guardar(vendedor);
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @GetMapping( path = "/{id}")
    public Optional<Vendedor> obtenerPorId(@PathVariable("id") Long id)
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


    @PostMapping(path="/login")
    public Object ObtenerPorUsuario(@RequestBody Usuario usuario)
    {
        try
        {
            return service.ObtenerPorUsuario(usuario);
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @DeleteMapping( path = "/{id}")
    public Boolean eliminarPorId(@PathVariable("id") Long id)
    {
        try
        {
            service.eliminar(id); 
            return true;   
        }
        catch(Exception ex)
        {
            return false;
        }   
    }    
}