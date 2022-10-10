package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Repositories.AutoRepository;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoAmericaExtranjero;

@Service
public class AutoService {
   @Autowired
   AutoRepository repository;
    
    public ArrayList<Auto> obtener()
    {
        return (ArrayList<Auto>) repository.findAll();
    }

    public Auto guardar(Auto auto)
    {
        return repository.save(auto);
    }

    public Optional<Auto> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public Auto calcularCosto(Auto auto)
    {
        EstrategiaCostoAmericaExtranjero ecn = new EstrategiaCostoAmericaExtranjero();
        auto = ecn.calcularCosto(auto);
        this.guardar(auto);
        return auto;
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
