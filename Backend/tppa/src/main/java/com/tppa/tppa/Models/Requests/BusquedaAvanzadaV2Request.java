package com.tppa.tppa.Models.Requests;

public class BusquedaAvanzadaV2Request {
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

    private Long idMarca;
    public Long getIdMarca() {
        return idMarca;
    }
    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }
    private Long idModelo;
    public Long getIdModelo() {
        return idModelo;
    }
    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
    }
    private Long idCliente;
    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCiente) {
        this.idCliente = idCiente;
    }
    private Long idVendedor;
    public Long getIdVendedor() {
        return idVendedor;
    }
    public void setIdVendedor(Long idVendedor) {
        this.idVendedor = idVendedor;
    }
    private Long idCategoria;
    public Long getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
