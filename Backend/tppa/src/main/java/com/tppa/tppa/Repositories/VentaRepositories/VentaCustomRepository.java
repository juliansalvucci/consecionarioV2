package com.tppa.tppa.Repositories.VentaRepositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.tppa.tppa.Models.Venta;
import com.tppa.tppa.Models.Requests.BusquedaAvanzadaRequest;

@Repository
public class VentaCustomRepository {
    EntityManager em;

    public VentaCustomRepository(EntityManager em){
        this.em = em;
    }

    public List<Venta> obtenerPorRangoDeCostosYFechas(@RequestBody BusquedaAvanzadaRequest bar) 
    {
        String CONSULTA = "SELECT venta FROM Venta venta WHERE venta.costo > :montoInicial AND venta.costo < :montoFinal AND  venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta";
        @SuppressWarnings("unchecked")
        List<Venta> registros = em.createQuery(CONSULTA)
        .setParameter("montoInicial", bar.getMontoInicial())
        .setParameter("montoFinal", bar.getMontoFinal())
        .setParameter("fechaDesde", bar.getFechaDesde())
        .setParameter("fechaHasta", bar.getFechaHasta())
        .getResultList();

        em.close();
        return registros;
    }
}
