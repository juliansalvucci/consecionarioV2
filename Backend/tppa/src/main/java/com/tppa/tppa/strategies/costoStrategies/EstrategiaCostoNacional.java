package com.tppa.tppa.strategies.costoStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Models.Rango;
import com.tppa.tppa.Services.RangoService;

@Service
public class EstrategiaCostoNacional implements IEstrategiaCosto 
{
    @Autowired(required = true)
    RangoService rangoService;

    @Override
    public Auto calcularCosto(Auto auto) 
    {
        Double precio = auto.getPrecio();

        Rango rango = rangoService.obtenerPorPrecio(precio);

        Double valor = rango.getValor();

        Double costo = valor + precio;

        var roundedCosto = Math.round(costo);

        auto.setCosto(Double.valueOf(roundedCosto));
        auto.setGanancia(valor);

        return auto;
    }
}
