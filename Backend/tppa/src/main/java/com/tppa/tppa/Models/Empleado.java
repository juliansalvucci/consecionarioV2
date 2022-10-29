package com.tppa.tppa.Models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.tppa.tppa.Models.Fathers.Persona;


@Entity
@DiscriminatorValue(value = "empleado")
public class Empleado extends Persona
{
    @NotNull public String legajo;
    @NotNull public String nombreUsuario;

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