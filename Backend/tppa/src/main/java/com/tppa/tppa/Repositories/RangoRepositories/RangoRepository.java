package com.tppa.tppa.Repositories.RangoRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Rango;


@Repository
public interface RangoRepository extends CrudRepository<Rango, Long> 
{

}
