package com.tppa.tppa.Repositories.RangoRepositories;

import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Rango;

@Repository
public interface IRangoCustomRepository {
    public Rango obtenerPorPrecio(Double precio);
}
