package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table (name="vendedor") 
public class Vendedor 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    @NotNull private String nombre;
    @NotNull private String apellido;
    @NotNull private String documento;
    @NotNull private String nombreUsuario;
    @NotNull private String contraseña;


    public void setContraseña(String contraseña) 
    {
        this.contraseña = contraseña;
    }

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public String getDocumento() 
    {
        return documento;
    }

    public void setDocumento(String documento) 
    {
        this.documento = documento;
    }

    public String getApellido() 
    {
        return apellido;
    }

    public void setApellido(String apellido) 
    {
        this.apellido = apellido;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }

    ///////////////////////////////////////////
    public Vendedor(){}

    public Vendedor(long id, @NotNull String nombre, @NotNull String apellido, @NotNull String documento,
            @NotNull String nombreUsuario, @NotNull String contraseña) {
        this.setId(id);
        this.setNombre(nombreUsuario);
        this.setApellido(apellido);
        this.setDocumento(documento);
        this.setNombreUsuario(nombreUsuario);
        this.setContraseña(contraseña);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
        result = prime * result + ((documento == null) ? 0 : documento.hashCode());
        result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
        result = prime * result + ((contraseña == null) ? 0 : contraseña.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vendedor other = (Vendedor) obj;
        if (id != other.id)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (apellido == null) {
            if (other.apellido != null)
                return false;
        } else if (!apellido.equals(other.apellido))
            return false;
        if (documento == null) {
            if (other.documento != null)
                return false;
        } else if (!documento.equals(other.documento))
            return false;
        if (nombreUsuario == null) {
            if (other.nombreUsuario != null)
                return false;
        } else if (!nombreUsuario.equals(other.nombreUsuario))
            return false;
        if (contraseña == null) {
            if (other.contraseña != null)
                return false;
        } else if (!contraseña.equals(other.contraseña))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vendedor [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento
                + ", nombreUsuario=" + nombreUsuario + ", contraseña=" + contraseña + "]";
    }
}