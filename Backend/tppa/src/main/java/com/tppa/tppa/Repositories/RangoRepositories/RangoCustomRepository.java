package com.tppa.tppa.Repositories.RangoRepositories;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tppa.tppa.Models.Rango;

@Repository
public class RangoCustomRepository 
{
    @Autowired
    EntityManager em;

    public Rango obtenerPorPrecio(Double precio) 
    {
        String CONSULTA = "SELECT rango FROM Rango rango WHERE rango.montoFinal >= :precio AND rango.montoInicial <= :precio";
        var registro = em.createQuery(CONSULTA)
        .setParameter("precio", precio)
        .getSingleResult();

        em.close();
        return (Rango)registro;
    }
}
