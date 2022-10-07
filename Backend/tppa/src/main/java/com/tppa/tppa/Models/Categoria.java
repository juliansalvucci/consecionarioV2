package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="categoria") 

public class Categoria 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;     
    @NotNull private String nombreCategoria;
    @NotNull private int porcentaje;
            
    public int getPorcentaje()
    {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) 
    {
        this.porcentaje = porcentaje;
    }

    public String getNombreCategoria()
    {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) 
    {
        this.nombreCategoria = nombreCategoria;
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
    public Categoria(){}

    public Categoria(String nombreCategoria,int porcentaje) 
    {
        this.setNombreCategoria(nombreCategoria);
        this.setPorcentaje(porcentaje);
    } 
}
