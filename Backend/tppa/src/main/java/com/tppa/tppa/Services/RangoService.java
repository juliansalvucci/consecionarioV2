package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Rango;
import com.tppa.tppa.Repositories.RangoRepositories.RangoCustomRepository;
import com.tppa.tppa.Repositories.RangoRepositories.RangoRepository;

@Service
public class RangoService  
{
    @Autowired
    RangoRepository repository;
    @Autowired
    RangoCustomRepository customRepository;
     
    public ArrayList<Rango> obtener()
    {
        return (ArrayList<Rango>) repository.findAll();
    }

    public Rango guardar(Rango rango)
    {
        return repository.save(rango);
    }

    public Optional<Rango> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public Rango obtenerPorPrecio(Double precio)
    {
        return customRepository.obtenerPorPrecio(precio);
    }

    public boolean eliminar(Long id) 
    {
        try
        {
            repository.deleteById(id);
            return true;
        }
        catch(Exception err)
        {
            return false;
        }
    }
}