package com.tppa.tppa.Models.ReportesModels;

public class DetalleVentasPorEmpleado {
    private String cantidadVentas;
    public String getCantidadVentas() {
        return cantidadVentas;
    }
    public void setCantidadVentas(String cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
    private String ganancia;
    public String getGanancia() {
        return ganancia;
    }
    public void setGanancia(String ganancia) {
        this.ganancia = ganancia;
    }
    private String modelo;
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    private String marca;
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    private String categoria;

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    private String nombreCliente;
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    private String apellidoCliente;
    public String getApellidoCliente() {
        return apellidoCliente;
    }
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }
    private String nombreVendedor;
    public String getNombreVendedor() {
        return nombreVendedor;
    }
    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }
    private String apellidoVendedor;
    public String getApellidoVendedor() {
        return apellidoVendedor;
    }
    public void setApellidoVendedor(String apellidoVendedor) {
        this.apellidoVendedor = apellidoVendedor;
    }
}
