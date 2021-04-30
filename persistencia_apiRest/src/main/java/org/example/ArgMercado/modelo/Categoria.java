package org.example.ArgMercado.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @NotBlank
    private String name;

    @OneToOne
    @JsonBackReference
    private Producto owner;

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

    public Producto getOwner() {
        return owner;
    }

    public void setOwner(Producto owner) {
        this.owner = owner;
    }

}
