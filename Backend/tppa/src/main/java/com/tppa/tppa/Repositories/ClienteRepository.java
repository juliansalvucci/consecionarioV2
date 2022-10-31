package com.tppa.tppa.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Cliente;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> 
{
    public abstract Optional<Cliente>findByDocumento(String documento);
}