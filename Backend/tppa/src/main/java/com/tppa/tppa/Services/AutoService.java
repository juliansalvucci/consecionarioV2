package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Repositories.AutoRepositories.IAutoRepository;
import com.tppa.tppa.strategies.costoStrategies.EstrategiasCostoDefinition;

@Service
public class AutoService 
{
   @Autowired
   IAutoRepository repository;
   @Autowired(required = true)
   EstrategiasCostoDefinition estrategiasCostoDefinition;
   
    public ArrayList<Auto> obtener()
    {
        return (ArrayList<Auto>) repository.findAll();
    }

    public Long guardar(Auto auto)
    {
        var autoAux = estrategiasCostoDefinition.calcularCosto(auto);
        var autoId = repository.save(autoAux);
        return autoId.getId();
    }

    public Optional<Auto> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public void eliminar(Long id) 
    {
        repository.deleteById(id);
    }
}
