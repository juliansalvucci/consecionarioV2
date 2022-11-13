package com.tppa.tppa.Models;

public class GananciaPorModeloYMarca {
    
    private int cantidadVentas;
    private Double costo;
    private String marca;

    public int getCantidadVentas() {
        return cantidadVentas;
    }
    
    public void setCantidadVentas(int cantidad) {
        this.cantidadVentas = cantidad;
    }

    public Double getCosto() {
        return costo;
    }
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
}
