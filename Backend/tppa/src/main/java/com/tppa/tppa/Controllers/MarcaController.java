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

import com.tppa.tppa.Models.Marca;
import com.tppa.tppa.Services.MarcaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/marca")
public class MarcaController 
{
    @Autowired
    MarcaService service;

    @GetMapping()
    public ArrayList<Marca> obtener()
    {
        return service.obtener();
    }

    @PostMapping()
    public Marca guardar(@RequestBody Marca marca)
    {
        return this.service.guardar(marca);
    }

    @GetMapping( path = "/{id}")
    public Optional<Marca> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public Boolean eliminarPorId(@PathVariable("id") Long id)
    {
        return this.service.eliminar(id);    
    }
    
}
