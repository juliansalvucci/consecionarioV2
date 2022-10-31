package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="pais", indexes = {
    @Index(columnList = "id",name = "idx"),
})

public class Pais 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;     
    @NotNull private String nombrePais;
    
    @OneToOne(fetch=FetchType.EAGER)
    private Categoria categoria;
            
    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria) 
    {
        this.categoria = categoria;
    }

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

    public Pais(String nombrePais, Categoria categoria) 
    {
        this.setNombrePais(nombrePais);
        this.setCategoria(categoria);
    } 
}
