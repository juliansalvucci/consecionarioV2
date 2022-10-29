package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Empleado;
import com.tppa.tppa.Repositories.EmpleadoRepository;

@Service
public class EmpleadoService{
    @Autowired
    EmpleadoRepository repository;
    
    public ArrayList<Empleado> obtener()
    {
        return (ArrayList<Empleado>) repository.findAll();
    }

    public Empleado guardar(Empleado empleado)
    {
        return repository.save(empleado);
    }

    public Optional<Empleado> obtenerPorId(Long id)
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