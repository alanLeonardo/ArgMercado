package org.example.ArgMercado.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @NotBlank
    private String name;

    public Categoria() {

    }

    public Categoria(@NotBlank String name) {
        this.name = name;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
