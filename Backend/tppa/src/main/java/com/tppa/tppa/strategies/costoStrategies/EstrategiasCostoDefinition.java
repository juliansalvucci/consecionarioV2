package com.tppa.tppa.strategies.costoStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Auto;

@Service
public class EstrategiasCostoDefinition
{
    @Autowired
    EstrategiaCostoAmericaExtranjero estrategiaCostoAmericaExtranjero;
    @Autowired
    EstrategiaCostoNacional estrategiaCostoNacional;

    public Auto calcularCosto(Auto auto)
    {
        var porcentaje = auto.getPais().getCategoria().getPorcentaje();
        Auto autoAux = new Auto();

        if (porcentaje > 0) 
        {
            autoAux = estrategiaCostoAmericaExtranjero.calcularCosto(auto);
        }

        if (porcentaje == 0) 
        {
            autoAux = estrategiaCostoNacional.calcularCosto(auto);
        }

        return autoAux;
    }
}
