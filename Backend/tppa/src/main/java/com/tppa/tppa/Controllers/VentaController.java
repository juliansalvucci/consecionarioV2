package com.tppa.tppa.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tppa.tppa.Models.Venta;
import com.tppa.tppa.Services.VentaService;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoNacional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/Venta")
public class VentaController  
{
    @Autowired
    VentaService service;

    @GetMapping()
    public ArrayList<Venta> obtener()
    {
        return service.obtener();
    }

    @PostMapping()
    public Venta guardar(@RequestBody Venta venta)
    {
        EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();
        venta = ecn.calcularCosto(venta);

        return this.service.guardar(venta);
    }

    @GetMapping( path = "/{id}")
    public Optional<Venta> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }   
}

