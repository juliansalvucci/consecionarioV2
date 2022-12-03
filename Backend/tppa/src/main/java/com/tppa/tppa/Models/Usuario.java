package com.tppa.tppa.Models;

public class Usuario 
{
    private String nombreUsuario;
    private String contraseña;

    public String getNombreUsuario() 
    {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) 
    {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() 
    {
        return contraseña;
    }

    public void setContraseña(String contraseña) 
    {
        this.contraseña = contraseña;
    }

    public Usuario(){}

    public Usuario(String nombreUsuario, String contraseña) 
    {
        this.setNombreUsuario(nombreUsuario);;
        this.setContraseña(contraseña);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        Usuario other = (Usuario) obj;
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
        return "Usuario [nombreUsuario=" + nombreUsuario + ", contraseña=" + contraseña + "]";
    }
}
