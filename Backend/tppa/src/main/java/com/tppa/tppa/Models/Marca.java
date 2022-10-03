package com.tppa.tppa.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="marca") 

public class Marca 
{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    @NotNull
    private String nombreMarca;
    @NotNull
    private Long idPais;

    @OneToOne
    private Pais pais;
  
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNombreMarca()
    {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) 
    {
        this.nombreMarca = nombreMarca;
    }

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }
    
    
    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }
    

    ///////////////////////////////////////////
    public Marca(){}

    public Marca(String nombreMarca, Long idPais, Pais pais) 
    {
        this.setNombreMarca(nombreMarca);
        this.setIdPais(idPais);
        this.setPais(pais);
    }   
}

