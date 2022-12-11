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

import com.tppa.tppa.Models.Categoria;
import com.tppa.tppa.Services.CategoriaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/categoria")
public class CategoriaController 
{
    @Autowired
    CategoriaService service;

    @GetMapping()
    public ArrayList<Categoria> obtener()
    {
        try 
        {
            return service.obtener();
        } 
        catch (Exception ex) 
        {
            return null;
        }
    }

    @PostMapping()
    public Long guardar(@RequestBody Categoria categoria)
    {
        try
        {
            return service.guardar(categoria);
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @GetMapping( path = "/{id}")
    public Optional<Categoria> obtenerPorId(@PathVariable("id") Long id)
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
        catch (Exception ex) 
        {
            return false;
        }  
    } 
}
