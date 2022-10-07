package com.tppa.tppa.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Categoria;


@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> 
{
    
}
