package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Repositories.AutoRepository;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoAmericaExtranjero;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoNacional;

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
        var procentaje = auto.getPais().getCategoria().getPorcentaje();
        Auto autoAux = new Auto();

        if(procentaje > 0){
            autoAux = this.calcularCostoAmericaExtranjero(auto);
        }

        if(procentaje == 0){
            autoAux = this.calcularCostoNacional(auto);
        }

         return repository.save(autoAux);
    }

    public Optional<Auto> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }

    public Auto calcularCostoAmericaExtranjero(Auto auto)
    {
        EstrategiaCostoAmericaExtranjero ec = new EstrategiaCostoAmericaExtranjero();
        auto = ec.calcularCosto(auto);
        return auto;
    }

    public Auto calcularCostoNacional(Auto auto)
    {
        EstrategiaCostoNacional ec = new EstrategiaCostoNacional();
        auto = ec.calcularCosto(auto);
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
