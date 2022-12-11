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

    public Long guardar(Cliente Cliente)
    {
        var clienteId = repository.save(Cliente);
        return clienteId.getId();
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