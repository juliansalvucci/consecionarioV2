package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="rango", indexes = {
    @Index(columnList = "id",name = "idx"),
})
public class Rango 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;  
    @NotNull private Double montoInicial;
    @NotNull private Double montoFinal;
    @NotNull private Double valor;
  

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public Double getMontoInicial() 
    {
        return montoInicial;
    }

    public void setMontoInicial(Double montoInicial) 
    {
        this.montoInicial = montoInicial;
    }

    public Double getMontoFinal() 
    {
        return montoFinal;
    }

    public void setMontoFinal(Double montoFinal)
    {
        this.montoFinal = montoFinal;
    }

    public Double getValor() 
    {
        return valor;
    }

    public void setValor(Double valor) 
    {
        this.valor = valor;
    }

    public Rango(){}

    public Rango(long id, @NotNull Double montoInicial, @NotNull Double montoFinal, @NotNull Double valor) {
        this.setId(id);;
        this.setMontoInicial(montoInicial);;
        this.setMontoFinal(montoFinal);;
        this.setValor(valor);;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((montoInicial == null) ? 0 : montoInicial.hashCode());
        result = prime * result + ((montoFinal == null) ? 0 : montoFinal.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
        Rango other = (Rango) obj;
        if (id != other.id)
            return false;
        if (montoInicial == null) {
            if (other.montoInicial != null)
                return false;
        } else if (!montoInicial.equals(other.montoInicial))
            return false;
        if (montoFinal == null) {
            if (other.montoFinal != null)
                return false;
        } else if (!montoFinal.equals(other.montoFinal))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rango [id=" + id + ", montoInicial=" + montoInicial + ", montoFinal=" + montoFinal + ", valor=" + valor
                + "]";
    }
}
