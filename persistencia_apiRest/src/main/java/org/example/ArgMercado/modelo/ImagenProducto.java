package org.example.ArgMercado.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class ImagenProducto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImagen;

    @NotBlank
    private String nombre;

    @NotBlank
    private String tipo;

    @Column(length = 400000)
    private byte[]  foto ;

    @ManyToOne
    @JsonBackReference
    private Producto owner;

    public ImagenProducto() {
    }

    public ImagenProducto(@NotBlank String name,@NotBlank String tipo, byte[]  foto) {
        this.nombre = name;
        this.tipo = tipo;
        this.foto = foto;
    }

    public Producto getOwner() {
        return owner;
    }

    public void setOwner(Producto owner) {
        this.owner = owner;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[]  getFoto() {
        return foto;
    }

    public void setFoto(byte[]  foto) {
        this.foto = foto;
    }

}
