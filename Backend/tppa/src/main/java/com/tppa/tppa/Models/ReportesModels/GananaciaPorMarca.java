package com.tppa.tppa.Models.ReportesModels;

public class GananaciaPorMarca {
    private String cantidadVentas;
    private String costo;
    private String marca;

    public String getCantidadVentas() {
        return cantidadVentas;
    }
    
    public void setCantidadVentas(String cantidad) {
        this.cantidadVentas = cantidad;
    }

    public String getCosto() {
        return costo;
    }
    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
}
