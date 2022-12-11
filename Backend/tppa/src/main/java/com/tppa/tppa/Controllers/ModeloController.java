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

import com.tppa.tppa.Models.Modelo;
import com.tppa.tppa.Services.ModeloService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/modelo")
public class ModeloController 
{
    @Autowired
    ModeloService service;

    @GetMapping()
    public ArrayList<Modelo> obtener()
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
    public Long guardar(@RequestBody Modelo modelo)
    {
        try
        {
            return service.guardar(modelo);
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @GetMapping( path = "/{id}")
    public Optional<Modelo> obtenerPorId(@PathVariable("id") Long id)
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