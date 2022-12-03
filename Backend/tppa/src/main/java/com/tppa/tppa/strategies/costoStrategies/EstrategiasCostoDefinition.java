package com.tppa.tppa.strategies.costoStrategies;

import com.tppa.tppa.Models.Auto;

public class EstrategiasCostoDefinition {
    
    public Auto calcularCosto(Auto auto){
        
        var procentaje = auto.getPais().getCategoria().getPorcentaje();
        Auto autoAux = new Auto();

        if (procentaje > 0) {
            EstrategiaCostoAmericaExtranjero ec = new EstrategiaCostoAmericaExtranjero();
            autoAux = ec.calcularCosto(auto);
        }

        if (procentaje == 0) {
            EstrategiaCostoNacional ec = new EstrategiaCostoNacional();
            autoAux = ec.calcularCosto(auto);
        }

        return autoAux;
    }
}
