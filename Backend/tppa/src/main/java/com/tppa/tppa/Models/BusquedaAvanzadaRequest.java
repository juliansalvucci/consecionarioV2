package com.tppa.tppa.Models;

public class BusquedaAvanzadaRequest {
    private String fechaDesde;
    public String getFechaDesde() {
        return fechaDesde;
    }
    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
    private String fechaHasta;
    public String getFechaHasta() {
        return fechaHasta;
    }
    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
    private Double montoInicial;
    public Double getMontoInicial() {
        return montoInicial;
    }
    public void setMontoInicial(Double montoInicial) {
        this.montoInicial = montoInicial;
    }
    private Double montoFinal;
    public Double getMontoFinal() {
        return montoFinal;
    }
    public void setMontoFinal(Double montoFinal) {
        this.montoFinal = montoFinal;
    }
}
