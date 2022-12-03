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

    public Marca(Long id,String nombreMarca) 
    {
        this.setId(id);
        this.setNombreMarca(nombreMarca);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nombreMarca == null) ? 0 : nombreMarca.hashCode());
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
        Marca other = (Marca) obj;
        if (id != other.id)
            return false;
        if (nombreMarca == null) {
            if (other.nombreMarca != null)
                return false;
        } else if (!nombreMarca.equals(other.nombreMarca))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Marca [id=" + id + ", nombreMarca=" + nombreMarca + "]";
    }   
}

