package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Pais;
import com.tppa.tppa.Repositories.IPaisRepository;

@Service
public class PaisService  
{
    @Autowired
    IPaisRepository repository;
    
    public ArrayList<Pais> obtener()
    {
        return (ArrayList<Pais>) repository.findAll();
    }

    public Long guardar(Pais pais)
    {
        var aux = repository.save(pais);
        return aux.getId();
    }

    public Optional<Pais> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public void eliminar(Long id) 
    {
       repository.deleteById(id);
    }
}