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
   
    public ArrayList<Auto> obtener()
    {
        return (ArrayList<Auto>) repository.findAll();
    }

    public Auto guardar(Auto auto)
    {
        EstrategiasCostoDefinition ecd = new EstrategiasCostoDefinition();

        var autoAux = ecd.calcularCosto(auto);

        return repository.save(autoAux);
    }

    public Optional<Auto> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public boolean eliminar(Long id) 
    {
        try
        {
            repository.deleteById(id);
            return true;
        }
        catch(Exception err)
        {
            return false;
        }
    }
}
