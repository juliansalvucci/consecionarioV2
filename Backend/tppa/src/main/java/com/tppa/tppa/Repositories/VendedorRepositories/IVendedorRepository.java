package com.tppa.tppa.Repositories.VendedorRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Vendedor;


@Repository
public interface IVendedorRepository extends CrudRepository<Vendedor, Long> 
{
    
}
