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

        Double ganancia = costo - precio;

        auto.setCosto(costo);
        auto.setGanancia(ganancia);

        return auto;
    }
}
