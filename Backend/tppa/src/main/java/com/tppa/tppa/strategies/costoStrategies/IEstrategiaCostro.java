package com.tppa.tppa.strategies.costoStrategies;

import com.tppa.tppa.Models.Venta;

public interface IEstrategiaCostro {
    public default void calcularCosto(Venta venta){}
}
