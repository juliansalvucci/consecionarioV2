package com.tppa.tppa.strategies.costoStrategies;
import com.tppa.tppa.Models.Auto;

public class EstrategiasCostoDefinition implements IEstrategiaCosto{
    
    public Auto calcularCosto(Auto auto){

        
        var porcentaje = auto.getPais().getCategoria().getPorcentaje();
        Auto autoAux = new Auto();

        if (porcentaje > 0) {
            EstrategiaCostoAmericaExtranjero ec = new EstrategiaCostoAmericaExtranjero();
            autoAux = ec.calcularCosto(auto);
        }

        if (porcentaje == 0) {
            EstrategiaCostoNacional ec = new EstrategiaCostoNacional();
            autoAux = ec.calcularCosto(auto);
        }

        return autoAux;
    }
}
