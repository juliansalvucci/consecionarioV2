package com.tppa.tppa.strategies.costoStrategies;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Models.Rango;

public interface IEstrategiaCosto {
    public default Auto calcularCosto(Auto auto){ return auto; }
    public default Auto calcularCosto(Auto auto,Rango rango){ return auto; }
}
