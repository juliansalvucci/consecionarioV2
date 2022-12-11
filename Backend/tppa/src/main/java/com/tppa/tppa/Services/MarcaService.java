package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Marca;
import com.tppa.tppa.Repositories.IMarcaReposiroty;

@Service
public class MarcaService 
{
    @Autowired
    IMarcaReposiroty repository;
    
    public ArrayList<Marca> obtener()
    {
        return (ArrayList<Marca>) repository.findAll();
    }

    public Long guardar(Marca marca)
    {
        var marcaAux = repository.save(marca);
        return marcaAux.getId();
    }

    public Optional<Marca> obtenerPorId(Long id)
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
