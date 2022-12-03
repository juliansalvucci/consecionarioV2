package com.tppa.tppa.strategies.costoStrategies;

import org.springframework.beans.factory.annotation.Autowired;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Models.Rango;
import com.tppa.tppa.Services.RangoService;


public class EstrategiaCostoNacional implements IEstrategiaCosto 
{
    @Autowired
    RangoService rangoService;

    @Override
    public Auto calcularCosto(Auto auto) 
    {

        Double precio = auto.getPrecio();

        Rango rango = rangoService.obtenerPorPrecio(precio);

        Double valor = rango.getValor();

        Double costo = valor + precio;

        auto.setCosto(costo);
        auto.setGanancia(valor);

        return auto;
    }
}
