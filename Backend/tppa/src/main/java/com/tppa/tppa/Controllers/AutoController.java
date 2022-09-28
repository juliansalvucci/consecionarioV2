package com.tppa.tppa.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Services.AutoService;

@RestController
@RequestMapping("/auto")
public class AutoController 
{
    @Autowired
    AutoService service;

    @GetMapping()
    public ArrayList<Auto> obtener()
    {
        return service.obtener();
    }

    @PostMapping()
    public Auto guardar(@RequestBody Auto auto)
    {
        return this.service.guardar(auto);
    }

    @GetMapping( path = "/{id}")
    public Optional<Auto> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id)
    {
        boolean ok = this.service.eliminar(id);
        
        if (ok)
        {
            return "Se eliminó el auto con id " + id;
        }
        else
        {
            return "No pudo eliminar el auto con id" + id;
        }
    }
    
}