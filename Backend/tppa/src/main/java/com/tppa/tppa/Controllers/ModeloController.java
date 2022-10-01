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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tppa.tppa.Models.Modelo;
import com.tppa.tppa.Services.ModeloService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/modelo")
public class ModeloController 
{
    //@Autowired
    ModeloService service;

    @Autowired // add here
    public ModeloController(ModeloService modeloService) {
       this.service = modeloService;
    }


    @GetMapping()
    public ArrayList<Modelo> obtener()
    {
        return service.obtener();
    }

    @PostMapping()
    public Modelo guardar(@RequestBody Modelo modelo)
    {
        return this.service.guardar(modelo);
    }

    @GetMapping( path = "/{id}")
    public Optional<Modelo> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }

    
    @GetMapping("/query")
    public ArrayList<Modelo> obtenerPorMarca(@RequestParam("idMarca") Long idMarca){
        return this.service.obtenerPorMarca(idMarca);
    }
    

    @DeleteMapping( path = "/{id}")
    public Boolean eliminarPorId(@PathVariable("id") Long id)
    {
        return this.service.eliminar(id);    
    }
    
}