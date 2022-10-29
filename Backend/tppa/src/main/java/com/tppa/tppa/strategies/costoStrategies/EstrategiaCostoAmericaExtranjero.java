package com.tppa.tppa.strategies.costoStrategies;

import com.tppa.tppa.Models.Auto;

public class EstrategiaCostoAmericaExtranjero implements IEstrategiaCosto{

    @Override
    public Auto calcularCosto(Auto auto) {

        Double precio = auto.getPrecio();

        int porcentaje = auto.getPais().getCategoria().getPorcentaje();

        Double costo = precio + ((precio * porcentaje) / 100);

        auto.setCosto(costo);

        return auto;
    }
    
}
