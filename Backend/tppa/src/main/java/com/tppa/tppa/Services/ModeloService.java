package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Modelo;
import com.tppa.tppa.Repositories.IModeloRepository;

@Service
public class ModeloService 
{
   @Autowired
   IModeloRepository repository;
    
    public ArrayList<Modelo> obtener()
    {
        return (ArrayList<Modelo>) repository.findAll();
    }

    public void guardar(Modelo modelo)
    {
        repository.save(modelo);
    }

    public Optional<Modelo> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public void eliminar(Long id) 
    {
       repository.deleteById(id);
    }
}