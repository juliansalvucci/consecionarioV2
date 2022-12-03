package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Cliente;
import com.tppa.tppa.Repositories.ClienteRepository;

@Service
public class ClienteService
{
    @Autowired
    ClienteRepository repository;
    
    public ArrayList<Cliente> obtener()
    {
        return (ArrayList<Cliente>) repository.findAll();
    }

    public Cliente guardar(Cliente Cliente)
    {
        return repository.save(Cliente);
    }

    public Optional<Cliente> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public Optional<Cliente> obtenerPorDocumento(String documento)
    {
        return repository.findByDocumento(documento);
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