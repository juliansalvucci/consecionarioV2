package com.tppa.tppa.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Empleado;


@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> 
{
    
}
