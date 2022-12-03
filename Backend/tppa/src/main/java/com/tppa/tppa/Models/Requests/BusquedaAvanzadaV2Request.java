package com.tppa.tppa.Models.Requests;

public class BusquedaAvanzadaV2Request 
{
    private String fechaDesde;
    private String fechaHasta;
    private Double montoInicial;
    private Double montoFinal;
    private Long idMarca;
    private Long idModelo;
    private Long idCliente;
    private Long idVendedor;
    private Long idCategoria;


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

    public Long getIdMarca() 
    {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) 
    {
        this.idMarca = idMarca;
    }

    public Long getIdModelo() 
    {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) 
    {
        this.idModelo = idModelo;
    }

    public Long getIdCliente() 
    {
        return idCliente;
    }
    public void setIdCliente(Long idCiente) 
    {
        this.idCliente = idCiente;
    }

    public Long getIdVendedor() 
    {
        return idVendedor;
    }

    public void setIdVendedor(Long idVendedor) 
    {
        this.idVendedor = idVendedor;
    }

    public Long getIdCategoria() 
    {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) 
    {
        this.idCategoria = idCategoria;
    }
}
