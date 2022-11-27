package com.tppa.tppa.Repositories.AutoRepositories;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Auto;

@Repository
public class AutoCustomRepository {
    EntityManager em;

    public AutoCustomRepository(EntityManager em){
        this.em = em;
    }

    public Auto obtenerPorIdAuto(Long id) 
    {
        String CONSULTA = "SELECT auto FROM Auto auto WHERE auto.id = :id";
        var registro = em.createQuery(CONSULTA)
        .setParameter("id", id)
        .getSingleResult();

        em.close();
        return (Auto)registro;
    }
}
