package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="venta") 

public class Venta 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    @NotNull private String fechaVenta;

    @OneToOne private Auto auto;
    @OneToOne private Cliente cliente;

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente) 
    {
        this.cliente = cliente;
    }

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }
    
    public Auto getAuto() 
    {
        return auto;
    }

    public void setAuto(Auto auto) 
    {
        this.auto = auto;
    }

    public String getFechaVenta() 
    {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) 
    {
        this.fechaVenta = fechaVenta;
    }

    ///////////////////////////////////////////
    public Venta(){}

    public Venta(long id, @NotNull String fechaVenta, Auto auto, Cliente cliente)
    {
        setId(id);
        setFechaVenta(fechaVenta);
        setAuto(auto);
        setCliente(cliente);
    }  
}