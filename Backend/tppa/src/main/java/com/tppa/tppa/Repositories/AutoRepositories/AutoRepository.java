package com.tppa.tppa.Repositories.AutoRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Auto;


@Repository
public interface AutoRepository extends CrudRepository<Auto, Long> 
{

}