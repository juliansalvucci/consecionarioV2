package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="marca", indexes = {
    @Index(columnList = "id",name = "idx"),
}) 

public class Marca 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    @NotNull private String nombreMarca;
  
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
    
    ///////////////////////////////////////////
    public Marca(){}

    public Marca(String nombreMarca, Long idPais, Pais pais) 
    {
        this.setNombreMarca(nombreMarca);
    }   
}

