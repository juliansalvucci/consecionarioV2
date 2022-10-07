package com.tppa.tppa.strategies.costoStrategies;

import com.tppa.tppa.Models.Venta;

public interface IEstrategiaCosto {
    public default Venta calcularCosto(Venta venta){ return venta; }
}
