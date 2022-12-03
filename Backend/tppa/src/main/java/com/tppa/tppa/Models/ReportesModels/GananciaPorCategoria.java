package com.tppa.tppa.Models.ReportesModels;

public class GananciaPorCategoria 
{
    private String cantidadVentas;
    private String costo;
    private String categoria;

    public String getCantidadVentas() 
    {
        return cantidadVentas;
    }
    public void setCantidadVentas(String cantidadVentas) 
    {
        this.cantidadVentas = cantidadVentas;
    }

    public String getCosto() 
    {
        return costo;
    }

    public void setCosto(String costo) 
    {
        this.costo = costo;
    }

    public String getCategoria() 
    {
        return categoria;
    }

    public void setCategoria(String categoria) 
    {
        this.categoria = categoria;
    }
}
