package com.tppa.tppa.strategies.costoStrategies;

import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Auto;

@Service
public class EstrategiaCostoAmericaExtranjero implements IEstrategiaCosto
{
    @Override
    public Auto calcularCosto(Auto auto) 
    {

        Double precio = auto.getPrecio();

        int porcentaje = auto.getPais().getCategoria().getPorcentaje();

        Double costo = precio + ((precio * porcentaje) / 100);

        var roundedCosto = Math.round(costo);

        Double ganancia =  costo - precio;

        var roundedGanancia = Math.round(ganancia);

        auto.setCosto(Double.valueOf(roundedCosto));
        auto.setGanancia(Double.valueOf(roundedGanancia));

        return auto;
    }
}
