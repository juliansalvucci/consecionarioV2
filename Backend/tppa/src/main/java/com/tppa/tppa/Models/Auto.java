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
@Table (name="auto", indexes = {
    @Index(columnList = "id",name = "idx"),
})

public class Auto 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id; 
    @NotNull private Double precio;
    @NotNull private Double costo;
    @NotNull private Double ganancia;
    @NotNull private Boolean vendido;

    @OneToOne(fetch=FetchType.EAGER) 
    private Modelo modelo;
    @OneToOne(fetch=FetchType.EAGER) 
    private Pais pais;

    public Double getGanancia()
    {
        return ganancia;
    }

    public void setGanancia(Double ganancia) 
    {
        this.ganancia = ganancia;
    }

    public Pais getPais() 
    {
        return pais;
    }

    public void setPais(Pais pais) 
    {
        this.pais = pais;
    }

    public Boolean getVendido() 
    {
        return vendido;
    }

    public void setVendido(Boolean vendido) 
    {
        this.vendido = vendido;
    }
    
    public Modelo getModelo() 
    {
        return modelo;
    }

    public void setModelo(Modelo modelo) 
    {
        this.modelo = modelo;
    }

    public Double getPrecio()
    {
        return precio;
    }

    public void setPrecio(Double precio) 
    {
        this.precio = precio;
    }

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public Double getCosto()
    {
        return costo;
    }

    public void setCosto(Double costo) 
    {
        this.costo = costo;
    }

    
    /////////////////////////////////////////// 
    public Auto(){}

    public Auto(long id, @NotNull Double precio, @NotNull Double costo, @NotNull Double ganancia,
            @NotNull Boolean vendido, Modelo modelo, Pais pais) {
        this.setId(id);
        this.setPrecio(precio);
        this.setCosto(costo);
        this.setGanancia(ganancia);
        this.setVendido(vendido);
        this.setModelo(modelo);
        this.setPais(pais);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((precio == null) ? 0 : precio.hashCode());
        result = prime * result + ((costo == null) ? 0 : costo.hashCode());
        result = prime * result + ((ganancia == null) ? 0 : ganancia.hashCode());
        result = prime * result + ((vendido == null) ? 0 : vendido.hashCode());
        result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
        result = prime * result + ((pais == null) ? 0 : pais.hashCode());
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
        Auto other = (Auto) obj;
        if (id != other.id)
            return false;
        if (precio == null) {
            if (other.precio != null)
                return false;
        } else if (!precio.equals(other.precio))
            return false;
        if (costo == null) {
            if (other.costo != null)
                return false;
        } else if (!costo.equals(other.costo))
            return false;
        if (ganancia == null) {
            if (other.ganancia != null)
                return false;
        } else if (!ganancia.equals(other.ganancia))
            return false;
        if (vendido == null) {
            if (other.vendido != null)
                return false;
        } else if (!vendido.equals(other.vendido))
            return false;
        if (modelo == null) {
            if (other.modelo != null)
                return false;
        } else if (!modelo.equals(other.modelo))
            return false;
        if (pais == null) {
            if (other.pais != null)
                return false;
        } else if (!pais.equals(other.pais))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Auto [id=" + id + ", precio=" + precio + ", costo=" + costo + ", ganancia=" + ganancia + ", vendido="
                + vendido + ", modelo=" + modelo + ", pais=" + pais + "]";
    }
}