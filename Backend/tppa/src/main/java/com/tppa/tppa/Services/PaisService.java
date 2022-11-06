package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Pais;
import com.tppa.tppa.Repositories.PaisRepository;

@Service
public class PaisService  {
    @Autowired
    PaisRepository repository;
     
    public ArrayList<Pais> obtener()
    {
        return (ArrayList<Pais>) repository.findAll();
    }
    

    public Pais guardar(Pais pais)
    {
        return repository.save(pais);
    }

    public Optional<Pais> obtenerPorId(Long id)
    {
        return repository.findById(id);
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