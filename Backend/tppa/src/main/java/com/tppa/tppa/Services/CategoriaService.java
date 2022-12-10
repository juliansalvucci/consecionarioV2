package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Categoria;
import com.tppa.tppa.Repositories.ICategoriaRepository;

@Service
public class CategoriaService  
{
    @Autowired
    ICategoriaRepository repository;
    
    public ArrayList<Categoria> obtener()
    {
        return (ArrayList<Categoria>) repository.findAll();
    }

    public void guardar(Categoria categoria)
    {
        repository.save(categoria);
    }

    public Optional<Categoria> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }


    public void eliminar(Long id) 
    {
        repository.deleteById(id);
    }
}