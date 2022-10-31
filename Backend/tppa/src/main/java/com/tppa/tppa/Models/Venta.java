package com.tppa.tppa.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="venta", indexes = {
    @Index(columnList = "id",name = "idx"),
})

public class Venta 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    @NotNull private String fechaVenta;
    @NotNull private Double costo;
    @NotNull private Double precio;
    @NotNull private int porcentaje;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_id", referencedColumnName = "id")
    private Auto auto;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    public int getPorcentaje() 
    {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) 
    {
        this.porcentaje = porcentaje;
    }

    public Double getPrecio() 
    {
        return precio;
    }

    public void setPrecio(Double precio) 
    {
        this.precio = precio;
    }

    public Double getCosto() 
    {
        return costo;
    }

    public void setCosto(Double costo) 
    {
        this.costo = costo;
    }

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