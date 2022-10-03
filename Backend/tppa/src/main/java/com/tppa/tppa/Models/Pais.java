package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="pais") 

public class Pais 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;     
    @NotNull  
    private String nombrePais;
            
    public String getNombrePais()
    {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) 
    {
        this.nombrePais = nombrePais;
    }

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    ///////////////////////////////////////////
    public Pais(){}

    public Pais(String nombrePais) 
    {
        this.setNombrePais(nombrePais);
    } 
}
