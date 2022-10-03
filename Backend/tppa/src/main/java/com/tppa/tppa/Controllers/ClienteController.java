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
        return service.obtener();
    }

    @PostMapping()
    public Cliente guardar(@RequestBody Cliente cliente)
    {
        return this.service.guardar(cliente);
    }

    @GetMapping( path = "/{id}")
    public Optional<Cliente> obtenerPorId(@PathVariable("id") Long id)
    {
        return this.service.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public Boolean eliminarPorId(@PathVariable("id") Long id)
    {
        return this.service.eliminar(id);    
    }
    
}