package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table (name="cliente") 

public class Cliente 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    @NotNull private String nombre;
    @NotNull private String apellido;
    @NotNull private String documento;

    public String getDocumento() 
    {
        return documento;
    }

    public void setDocumento(String documento) 
    {
        this.documento = documento;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getApellido() 
    {
        return apellido;
    }

    public void setApellido(String apellido) 
    {
        this.apellido = apellido;
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
    public Cliente(){}

    public Cliente(Long id, String nombre, String apellido, String documento) 
    {
        this.setId(id);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDocumento(documento);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
        result = prime * result + ((documento == null) ? 0 : documento.hashCode());
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
        Cliente other = (Cliente) obj;
        if (id != other.id)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (apellido == null) {
            if (other.apellido != null)
                return false;
        } else if (!apellido.equals(other.apellido))
            return false;
        if (documento == null) {
            if (other.documento != null)
                return false;
        } else if (!documento.equals(other.documento))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento + "]";
    } 
}
