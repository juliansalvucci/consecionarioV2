package com.tppa.tppa.Repositories.VendedorRepositories;

import org.springframework.stereotype.Repository;

import com.tppa.tppa.Models.Usuario;

@Repository
public interface IVendedorCustomRepository 
{
    public Object ObtenerPorUsuario(Usuario usuario);
}
