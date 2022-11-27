package com.tppa.tppa.strategies.costoStrategies;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Models.Rango;


public class EstrategiaCostoNacional implements IEstrategiaCosto {
    @Override
    public Auto calcularCosto(Auto auto,Rango rango) {

        Double precio = auto.getPrecio();

        Double valor = rango.getValor();

        Double costo = valor + precio;

        auto.setCosto(costo);
        auto.setGanancia(valor);

        return auto;
    }
}
