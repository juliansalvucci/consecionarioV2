package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table (name="empleado") 
public class Empleado 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    @NotNull private String nombre;
    @NotNull private String apellido;
    @NotNull private String documento;
    @NotNull public String legajo;
    @NotNull public String nombreUsuario;

    
    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public String getDocumento() 
    {
        return documento;
    }

    public void setDocumento(String documento) 
    {
        this.documento = documento;
    }

    public String getApellido() 
    {
        return apellido;
    }

    public void setApellido(String apellido) 
    {
        this.apellido = apellido;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getLegajo() 
    {
        return legajo;
    }

    public void setLegajo(String legajo)
    {
        this.legajo = legajo;
    }

    public String getNombreUsuario()
    {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }

    ///////////////////////////////////////////
    public Empleado(){}

    public Empleado(String Legajo) 
    {
        this.setLegajo(Legajo);
    } 
}