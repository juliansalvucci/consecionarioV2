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
    @NotNull private Double ganancia;
    @NotNull private int porcentaje;

    @OneToOne(fetch = FetchType.EAGER) private Auto auto;
    @OneToOne private Cliente cliente;
    @OneToOne private Vendedor empleado;

    public Double getGanancia() 
    {
        return ganancia;
    }

    public void setGanancia(Double ganancia) 
    {
        this.ganancia = ganancia;
    }

    public Vendedor getEmpleado() 
    {
        return empleado;
    }

    public void setEmpleado(Vendedor empleado) 
    {
        this.empleado = empleado;
    }

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
        //setCliente(cliente);
    }  
}