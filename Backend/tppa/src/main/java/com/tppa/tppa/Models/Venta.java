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

    public Venta(long id, @NotNull String fechaVenta, @NotNull Double costo, @NotNull Double precio,
            @NotNull Double ganancia, @NotNull int porcentaje, Auto auto, Cliente cliente, Vendedor empleado) {
        this.setId(id);;
        this.setFechaVenta(fechaVenta);
        this.setCosto(costo);
        this.setPrecio(precio);
        this.setGanancia(ganancia);
        this.setPorcentaje(porcentaje);
        this.setAuto(auto);
        this.setCliente(cliente);
        this.setEmpleado(empleado);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((fechaVenta == null) ? 0 : fechaVenta.hashCode());
        result = prime * result + ((costo == null) ? 0 : costo.hashCode());
        result = prime * result + ((precio == null) ? 0 : precio.hashCode());
        result = prime * result + ((ganancia == null) ? 0 : ganancia.hashCode());
        result = prime * result + porcentaje;
        result = prime * result + ((auto == null) ? 0 : auto.hashCode());
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        result = prime * result + ((empleado == null) ? 0 : empleado.hashCode());
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
        Venta other = (Venta) obj;
        if (id != other.id)
            return false;
        if (fechaVenta == null) {
            if (other.fechaVenta != null)
                return false;
        } else if (!fechaVenta.equals(other.fechaVenta))
            return false;
        if (costo == null) {
            if (other.costo != null)
                return false;
        } else if (!costo.equals(other.costo))
            return false;
        if (precio == null) {
            if (other.precio != null)
                return false;
        } else if (!precio.equals(other.precio))
            return false;
        if (ganancia == null) {
            if (other.ganancia != null)
                return false;
        } else if (!ganancia.equals(other.ganancia))
            return false;
        if (porcentaje != other.porcentaje)
            return false;
        if (auto == null) {
            if (other.auto != null)
                return false;
        } else if (!auto.equals(other.auto))
            return false;
        if (cliente == null) {
            if (other.cliente != null)
                return false;
        } else if (!cliente.equals(other.cliente))
            return false;
        if (empleado == null) {
            if (other.empleado != null)
                return false;
        } else if (!empleado.equals(other.empleado))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Venta [id=" + id + ", fechaVenta=" + fechaVenta + ", costo=" + costo + ", precio=" + precio
                + ", ganancia=" + ganancia + ", porcentaje=" + porcentaje + ", auto=" + auto + ", cliente=" + cliente
                + ", empleado=" + empleado + "]";
    }
}