package com.tppa.tppa.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Marca;


@Repository
public interface MarcaReposiroty extends CrudRepository<Marca, Long> 
{
    
}

//CrudRepository ya especifica los métodos de comunicación con BD.
