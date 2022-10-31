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

    public Cliente(String nombre, String documento) 
    {
        this.setNombre(nombre);
        this.setDocumento(documento);
    } 
}
