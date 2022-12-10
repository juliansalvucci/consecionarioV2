package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Cliente;
import com.tppa.tppa.Repositories.IClienteRepository;

@Service
public class ClienteService
{
    @Autowired
    IClienteRepository repository;
    
    public ArrayList<Cliente> obtener()
    {
        return (ArrayList<Cliente>) repository.findAll();
    }

    public void guardar(Cliente Cliente)
    {
        repository.save(Cliente);
    }

    public Optional<Cliente> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public Optional<Cliente> obtenerPorDocumento(String documento)
    {
        return repository.findByDocumento(documento);
    }

    public void eliminar(Long id) 
    {   
       repository.deleteById(id);   
    }
}