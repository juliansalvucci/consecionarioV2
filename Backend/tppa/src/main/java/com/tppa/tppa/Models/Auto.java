package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="auto") 
public class Auto 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id; 
    private Double precio;
    private Long idModelo;
    private Long idMarca;

    
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

    public Long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) 
    {
        this.idModelo = idModelo;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) 
    {
        this.idMarca = idMarca;
    }
    
    /////////////////////////////////////////// 
    public Auto(){}

    public Auto(Double precio, Long idModelo) 
    {
        this.setPrecio(precio);
        this.setIdModelo(idModelo);
        this.setIdMarca(idModelo);
    }  
}