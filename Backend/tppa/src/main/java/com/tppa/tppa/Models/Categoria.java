package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="categoria", indexes = {
    @Index(columnList = "id",name = "idx"),
}) 

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

    public Categoria(Long id, String nombreCategoria,int porcentaje) 
    {
        this.setId(id);
        this.setNombreCategoria(nombreCategoria);
        this.setPorcentaje(porcentaje);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nombreCategoria == null) ? 0 : nombreCategoria.hashCode());
        result = prime * result + porcentaje;
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
        Categoria other = (Categoria) obj;
        if (id != other.id)
            return false;
        if (nombreCategoria == null) {
            if (other.nombreCategoria != null)
                return false;
        } else if (!nombreCategoria.equals(other.nombreCategoria))
            return false;
        if (porcentaje != other.porcentaje)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nombreCategoria=" + nombreCategoria + ", porcentaje=" + porcentaje + "]";
    } 
}
