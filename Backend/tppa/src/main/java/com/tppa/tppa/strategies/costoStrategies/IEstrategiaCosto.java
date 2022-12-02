package com.tppa.tppa.strategies.costoStrategies;

import com.tppa.tppa.Models.Auto;

public interface IEstrategiaCosto {
    public default Auto calcularCosto(Auto auto){ return auto; }
}
