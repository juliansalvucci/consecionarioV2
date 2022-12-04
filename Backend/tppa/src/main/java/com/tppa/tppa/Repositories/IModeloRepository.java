package com.tppa.tppa.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tppa.tppa.Models.Modelo;


@Repository
public interface IModeloRepository  extends CrudRepository<Modelo, Long> 
{
    //Método no utilizado. Se resuelve en frontend.
    //public abstract ArrayList<Modelo>findByIdMarca(Long idMarca); //El método va a funcionar correctamente siempre y cuando el nombre sea representativo.
}
