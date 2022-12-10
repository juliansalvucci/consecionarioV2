package com.tppa.tppa.Repositories.VendedorRepositories;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import com.tppa.tppa.Models.Usuario;

@Repository
public class VendedorCustomRepository 
{
    EntityManager em;

    public VendedorCustomRepository(EntityManager em)
    {
        this.em = em;
    }

    public Object ObtenerPorUsuario(Usuario usuario)
    {
        String CONSULTA = "SELECT vendedor FROM Vendedor vendedor WHERE vendedor.nombreUsuario = :nombreUsuario AND vendedor.contraseña = :contraseña";
        var registro = em.createQuery(CONSULTA)
        .setParameter("nombreUsuario", usuario.getNombreUsuario())
        .setParameter("contraseña", usuario.getContraseña())
        .getSingleResult();

        em.close();
        return registro;
    }
}
