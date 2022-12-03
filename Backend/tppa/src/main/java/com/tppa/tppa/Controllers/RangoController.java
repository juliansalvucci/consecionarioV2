package com.tppa.tppa.Controllers;

import java.util.List;
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

import com.tppa.tppa.Models.Rango;
import com.tppa.tppa.Services.RangoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/rango")
public class RangoController 
{
    @Autowired
    RangoService service;
   
    @GetMapping()
    public List<Rango> obtener()
    {
        return service.obtener();
    }
    
    @PostMapping()
    public Rango guardar(@RequestBody Rango rango)
    {
        return service.guardar(rango);
    }

    @GetMapping( path = "/{id}")
    public Optional<Rango> obtenerPorId(@PathVariable("id") Long id)
    {
        return service.obtenerPorId(id);
    }

    @GetMapping( path = "obtenerPorPrecio/{precio}")
    public Rango obtenerPorPrecio(@PathVariable("precio") Double precio)
    {
        return service.obtenerPorPrecio(precio);
    }

    @DeleteMapping( path = "/{id}")
    public Boolean eliminarPorId(@PathVariable("id") Long id)
    {
        return service.eliminar(id);    
    }
    
}