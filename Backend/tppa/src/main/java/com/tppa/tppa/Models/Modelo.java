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
@Table (name="modelo", indexes = {
    @Index(columnList = "id",name = "idx"),
}) 

public class Modelo 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;    
    @NotNull private String nombreModelo;
  
    @OneToOne(fetch=FetchType.EAGER) 
    private Marca marca;
    
    public Marca getMarca()
    {
        return marca;
    }

    public void setMarca(Marca marca) 
    {
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


    ///////////////////////////////////////////
    public Modelo(){}

    public Modelo(Long id,String nombreModelo, Marca marca) 
    {
        this.setId(id);
        this.setNombreModelo(nombreModelo);
        this.setMarca(marca);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nombreModelo == null) ? 0 : nombreModelo.hashCode());
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Modelo other = (Modelo) obj;
        if (id != other.id)
            return false;
        if (nombreModelo == null) {
            if (other.nombreModelo != null)
                return false;
        } else if (!nombreModelo.equals(other.nombreModelo))
            return false;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Modelo [id=" + id + ", nombreModelo=" + nombreModelo + ", marca=" + marca + "]";
    }  
}