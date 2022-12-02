package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tppa.tppa.Models.Usuario;
import com.tppa.tppa.Models.Vendedor;
import com.tppa.tppa.Repositories.VendedorRepositories.VendedorCustomRepository;
import com.tppa.tppa.Repositories.VendedorRepositories.VendedorRepository;

@Service
public class EmpleadoService{
    @Autowired
    VendedorRepository repository;
    @Autowired
    VendedorCustomRepository customRepository;
    
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

    public Object ObtenerPorUsuario(@RequestHeader Usuario usuario)
    {
        return customRepository.ObtenerPorUsuario(usuario);
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