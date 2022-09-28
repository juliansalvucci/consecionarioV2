package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="marca") 

public class Marca 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    private String nombreMarca;
    private Long idPais;
    
    public String getNombreMarca()
    {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) 
    {
        this.nombreMarca = nombreMarca;
    }

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }
    
    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    ///////////////////////////////////////////
    public Marca(){}

    public Marca(String nombreMarca, Long idPais) 
    {
        this.setNombreMarca(nombreMarca);
        this.setIdPais(idPais);
    }   
}

