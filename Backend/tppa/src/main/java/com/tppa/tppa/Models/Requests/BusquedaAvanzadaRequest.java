package com.tppa.tppa.Models.Requests;

public class BusquedaAvanzadaRequest 
{
    private String fechaDesde;
    private String fechaHasta;
    private Double montoInicial;
    private Double montoFinal;

    public String getFechaDesde() 
    {
        return fechaDesde;
    }
    public void setFechaDesde(String fechaDesde) 
    {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() 
    {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) 
    {
        this.fechaHasta = fechaHasta;
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
}
