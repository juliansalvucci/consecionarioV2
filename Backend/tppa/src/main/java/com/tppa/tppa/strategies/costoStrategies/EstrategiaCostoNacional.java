package com.tppa.tppa.strategies.costoStrategies;

import com.tppa.tppa.Models.Venta;

public class EstrategiaCostoNacional implements IEstrategiaCosto{

    @Override
    public Venta calcularCosto(Venta venta) {
        Double precio = venta.getAuto().getPrecio();

        int porcentaje = venta.getAuto().getModelo().getMarca().getPais().getCategoria().getPorcentaje();

        Double costo = (precio * porcentaje) / 100;

        venta.setCosto(costo);

        return venta;
    }
    
}
