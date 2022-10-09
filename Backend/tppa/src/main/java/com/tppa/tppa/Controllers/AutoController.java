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

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Services.AutoService;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoAmericaExtranjero;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
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
        EstrategiaCostoAmericaExtranjero ecn = new EstrategiaCostoAmericaExtranjero();
        auto = ecn.calcularCosto(auto);
        
        return this.service.guardar(auto);
    }

    @GetMapping( path = "/{id}")
    public Optional<Auto> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public Boolean eliminarPorId(@PathVariable("id") Long id)
    {
        return this.service.eliminar(id);    
    }
    
}