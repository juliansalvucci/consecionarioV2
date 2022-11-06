package com.tppa.tppa.DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import com.tppa.tppa.Models.Pais;

public class PaisDao {

    private EntityManager em;

    public PaisDao(EntityManager em){
        this.em = em;    
    }

    public List<Pais> get() {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pais> cr = cb.createQuery(Pais.class);
        Root<Pais> root = cr.from(Pais.class);
        cr.select(root);

        TypedQuery<Pais> query = em.createQuery(cr);
        List<Pais> results = query.getResultList();

        return results;
    }
}
