package com.tppa.tppa.strategies.costoStrategies;

import com.tppa.tppa.Models.Auto;

public class EstrategiaCostoNacional implements IEstrategiaCosto {
    @Override
    public Auto calcularCosto(Auto auto) {

        Double precio = auto.getPrecio();
        Double costo;
        

        if (precio > 0 && precio < 50000) {
            costo = precio + 5000;
            auto.setCosto(costo);
            auto.setGanancia(costo, precio);
        }

        if (precio > 50000 && precio < 100000) {
            costo = precio + 10000;
            auto.setCosto(costo);
            auto.setGanancia(costo, precio);
        }

        if (precio > 100000 && precio < 1000000) {
            costo = precio + 50000;
            auto.setCosto(costo);
            auto.setGanancia(costo, precio);
        }

        

        return auto;
    }
}
