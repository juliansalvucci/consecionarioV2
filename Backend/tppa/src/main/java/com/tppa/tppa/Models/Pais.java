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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nombrePais == null) ? 0 : nombrePais.hashCode());
        result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
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
        Pais other = (Pais) obj;
        if (id != other.id)
            return false;
        if (nombrePais == null) {
            if (other.nombrePais != null)
                return false;
        } else if (!nombrePais.equals(other.nombrePais))
            return false;
        if (categoria == null) {
            if (other.categoria != null)
                return false;
        } else if (!categoria.equals(other.categoria))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pais [id=" + id + ", nombrePais=" + nombrePais + ", categoria=" + categoria + "]";
    } 
}
