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


import com.tppa.tppa.Models.Cliente;
import com.tppa.tppa.Services.ClienteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/cliente")
public class ClienteController 
{
    @Autowired
    ClienteService service;

    @GetMapping()
    public ArrayList<Cliente> obtener()
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
    public Boolean guardar(@RequestBody Cliente cliente)
    {
        try
        {
            service.guardar(cliente);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    @GetMapping( path = "obtenerPorId/{id}")
    public Optional<Cliente> obtenerPorId(@PathVariable("id") Long id)
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

    @GetMapping( value = "obtenerPorDocumento/{documento}")
    public Optional<Cliente> obtenerPorDocumento(@PathVariable("documento") String documento)
    {
        try
        {
            return service.obtenerPorDocumento(documento);
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