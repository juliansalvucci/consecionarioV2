package com.tppa.tppa.Models.ReportesModels;

public class GananciaPorEmpleado 
{
    private String cantidadVentas;
    private String costo;
    private String empleadoNombre;
    private String empleadoApellido;

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

    public String getEmpleadoNombre() 
    {
        return empleadoNombre;
    }

    public void setEmpleadoNombre(String empleadoNombre) 
    {
        this.empleadoNombre = empleadoNombre;
    }

    public String getEmpleadoApellido() 
    {
        return empleadoApellido;
    }

    public void setEmpleadoApellido(String empleadoApellido) 
    {
        this.empleadoApellido = empleadoApellido;
    }
}
