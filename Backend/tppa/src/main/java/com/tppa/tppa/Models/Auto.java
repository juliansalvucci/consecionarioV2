package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="auto") 
public class Auto 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id; 
    @NotNull private Double precio;
    @NotNull private Double costo;
    @NotNull private Boolean vendido;

    @OneToOne private Modelo modelo;

    public Boolean getVendido() 
    {
        return vendido;
    }

    public void setVendido(Boolean vendido) 
    {
        this.vendido = vendido;
    }
    
    public Modelo getModelo() 
    {
        return modelo;
    }

    public void setModelo(Modelo modelo) 
    {
        this.modelo = modelo;
    }

    public Double getPrecio()
    {
        return precio;
    }

    public void setPrecio(Double precio) 
    {
        this.precio = precio;
    }

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public Double getCosto()
    {
        return costo;
    }

    public void setCosto(Double costo) 
    {
        this.costo = costo;
    }

    
    /////////////////////////////////////////// 
    public Auto(){}

    public Auto(Double precio,Double costo, Modelo modelo, Boolean vendido) 
    {
        this.setPrecio(precio);
        this.setCosto(costo);
        this.setModelo(modelo);
        this.setVendido(vendido);
    }  
}