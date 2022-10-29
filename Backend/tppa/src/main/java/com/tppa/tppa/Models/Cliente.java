package com.tppa.tppa.Models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.tppa.tppa.Models.Fathers.Persona;

@Entity
@DiscriminatorValue(value = "cliente")
public class Cliente extends Persona
{
    ///////////////////////////////////////////
    public Cliente(){}

    public Cliente(String nombre, String documento) 
    {
        this.setNombre(nombre);
        this.setDocumento(documento);
    } 
}
