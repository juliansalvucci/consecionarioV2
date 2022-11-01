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

import com.tppa.tppa.Models.Empleado;
import com.tppa.tppa.Services.EmpleadoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/Empleado")
public class EmpleadoController 
{
    @Autowired
    EmpleadoService service;

    @GetMapping()
    public ArrayList<Empleado> obtener()
    {
        return service.obtener();
    }

    @PostMapping()
    public Empleado guardar(@RequestBody Empleado empleado)
    {
        return this.service.guardar(empleado);
    }

    @GetMapping( path = "/{id}")
    public Optional<Empleado> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public Boolean eliminarPorId(@PathVariable("id") Long id)
    {
        return this.service.eliminar(id);    
    }    
}