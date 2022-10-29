package com.tppa.tppa.Models.Fathers;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="persona", catalog = "consecionario1")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType =  DiscriminatorType.STRING )
public class Persona implements java.io.Serializable{
    @Id @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
    private long id;
    @NotNull private String nombre;
    @Column(unique=true) @NotNull private String documento;

    public String getDocumento() 
    {
        return documento;
    }
    public void setDocumento(String documento) 
    {
        this.documento = documento;
    }
    public long getId() 
    {
        return id;
    }
    public void setId(long id) 
    {
        this.id = id;
    }
    public String getNombre() 
    {
        return nombre;
    }
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
}
