package com.tppa.tppa.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Venta;


@Repository
public interface VentaRepository extends CrudRepository<Venta, Long> 
{       
    
}