package com.tppa.tppa.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Pais;


@Repository
public interface PaisRepository  extends CrudRepository<Pais, Long> 
{

}