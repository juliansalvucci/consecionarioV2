package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Venta;
import com.tppa.tppa.Repositories.VentaRepository;

@Service
public class VentaService
{
    @Autowired
    VentaRepository repository;
    
    public ArrayList<Venta> obtener()
    {
        return (ArrayList<Venta>) repository.findAll();
    }

    public Venta guardar(Venta venta)
    {
        return repository.save(venta);
    }

    public Optional<Venta> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }  
}
