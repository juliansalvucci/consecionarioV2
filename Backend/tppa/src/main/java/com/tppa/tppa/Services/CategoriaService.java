package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Categoria;
import com.tppa.tppa.Repositories.CategoriaRepository;

@Service
public class CategoriaService  
{
    @Autowired
    CategoriaRepository repository;
    
    public ArrayList<Categoria> obtener()
    {
        return (ArrayList<Categoria>) repository.findAll();
    }

    public Categoria guardar(Categoria categoria)
    {
        return repository.save(categoria);
    }

    public Optional<Categoria> obtenerPorId(Long id)
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