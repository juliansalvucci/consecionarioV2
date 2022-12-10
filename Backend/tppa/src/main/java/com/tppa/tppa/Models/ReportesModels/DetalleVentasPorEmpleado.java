package com.tppa.tppa.Models.ReportesModels;

public class DetalleVentasPorEmpleado
{
    private String cantidadVentas;
    private String ganancia;
    private String modelo;
    private String marca;
    private String categoria;
    private String nombreCliente;
    private String apellidoCliente;
    private String nombreVendedor;
    private String apellidoVendedor;

    public String getCantidadVentas() 
    {
        return cantidadVentas;
    }
    public void setCantidadVentas(String cantidadVentas) 
    {
        this.cantidadVentas = cantidadVentas;
    }

    public String getGanancia() 
    {
        return ganancia;
    }
    public void setGanancia(String ganancia) 
    {
        this.ganancia = ganancia;
    }

    public String getModelo() 
    {
        return modelo;
    }

    public void setModelo(String modelo) 
    {
        this.modelo = modelo;
    }

    public String getMarca() 
    {
        return marca;
    }
    
    public void setMarca(String marca) 
    {
        this.marca = marca;
    }

    public String getCategoria() 
    {
        return categoria;
    }
    public void setCategoria(String categoria) 
    {
        this.categoria = categoria;
    }

    public String getNombreCliente() 
    {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) 
    {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() 
    {
        return apellidoCliente;
    }
    public void setApellidoCliente(String apellidoCliente) 
    {
        this.apellidoCliente = apellidoCliente;
    }

    public String getNombreVendedor() 
    {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) 
    {
        this.nombreVendedor = nombreVendedor;
    }

    public String getApellidoVendedor() 
    {
        return apellidoVendedor;
    }

    public void setApellidoVendedor(String apellidoVendedor) 
    {
        this.apellidoVendedor = apellidoVendedor;
    }
}
