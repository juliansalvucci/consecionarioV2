package com.tppa.tppa.Repositories.VentaRepositories;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Venta;


@Repository
public interface IVentaRepository extends CrudRepository<Venta, Long> 
{       
    
}