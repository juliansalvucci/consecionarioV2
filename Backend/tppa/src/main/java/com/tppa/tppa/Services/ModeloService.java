package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Modelo;
import com.tppa.tppa.Repositories.ModeloRepository;

@Service
public class ModeloService {
   @Autowired
   ModeloRepository repository;
    
    public ArrayList<Modelo> obtener()
    {
        return (ArrayList<Modelo>) repository.findAll();
    }

    public Modelo guardar(Modelo modelo)
    {
        return repository.save(modelo);
    }

    public Optional<Modelo> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public ArrayList<Modelo> obtenerPorMarca(Long idMarca) {
        return repository.findByIdMarca(idMarca);
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