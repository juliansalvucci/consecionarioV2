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
public class Rango {

    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;    

    @NotNull private Double montoInicial;
    public Double getMontoInicial() {
        return montoInicial;
    }
    public void setMontoInicial(Double montoInicial) {
        this.montoInicial = montoInicial;
    }

    @NotNull private Double montoFinal;
    public Double getMontoFinal() {
        return montoFinal;
    }
    public void setMontoFinal(Double montoFinal) {
        this.montoFinal = montoFinal;
    }
    @NotNull private Double valor;
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
}
