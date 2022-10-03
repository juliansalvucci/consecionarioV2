package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="modelo") 

public class Modelo 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;    
    @NotNull   
    private String nombreModelo;
    @NotNull
    private Long idMarca;

    @OneToOne
    private Marca marca;
    
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getNombreModelo()
    {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) 
    {
        this.nombreModelo = nombreModelo;
    }

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) 
    {
        this.idMarca = idMarca;
    }

    ///////////////////////////////////////////
    public Modelo(){}

    public Modelo(String nombreModelo, Long idMarca, Marca marca) 
    {
        this.setNombreModelo(nombreModelo);
        this.setIdMarca(idMarca);
        this.setMarca(marca);
    }  
}