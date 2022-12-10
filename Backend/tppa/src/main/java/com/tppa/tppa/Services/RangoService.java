package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Rango;
import com.tppa.tppa.Repositories.RangoRepositories.IRangoCustomRepository;
import com.tppa.tppa.Repositories.RangoRepositories.IRangoRepository;

@Service
public class RangoService  
{
    @Autowired
    IRangoRepository repository;
    @Autowired
    IRangoCustomRepository customRepository;
     
    public ArrayList<Rango> obtener()
    {
        return (ArrayList<Rango>) repository.findAll();
    }

    public void guardar(Rango rango)
    {
        repository.save(rango);
    }

    public Optional<Rango> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public Rango obtenerPorPrecio(Double precio)
    {
        return customRepository.obtenerPorPrecio(precio);
    }

    public void eliminar(Long id) 
    {
        repository.deleteById(id);    
    }
}