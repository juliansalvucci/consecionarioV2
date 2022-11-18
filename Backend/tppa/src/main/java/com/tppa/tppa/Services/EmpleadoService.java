package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Vendedor;
import com.tppa.tppa.Repositories.VendedorRepository;

@Service
public class EmpleadoService{
    @Autowired
    VendedorRepository repository;
    
    public ArrayList<Vendedor> obtener()
    {
        return (ArrayList<Vendedor>) repository.findAll();
    }

    public Vendedor guardar(Vendedor empleado)
    {
        return repository.save(empleado);
    }

    public Optional<Vendedor> obtenerPorId(Long id)
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