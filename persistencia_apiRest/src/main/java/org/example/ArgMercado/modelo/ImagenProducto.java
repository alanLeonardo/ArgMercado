package org.example.ArgMercado.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ImagenProducto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idImagen;

    private String nombre;

    private String tipo;

    @Column(length = 400000)
    private byte[]  foto ;

    @ManyToOne
    @JsonBackReference
    private Producto owner;

    public ImagenProducto() {
    }

    public ImagenProducto(int idImagen, String name, String tipo, byte[]  foto, Producto owner) {
        this.idImagen = idImagen;
        this.nombre = name;
        this.tipo = tipo;
        this.foto = foto;
        this.owner = owner;
    }
    public ImagenProducto(int idImagen, String name, String tipo, byte[]  foto) {
        this.idImagen = idImagen;
        this.nombre = name;
        this.tipo = tipo;
        this.foto = foto;
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

    public Producto getOwner() {
        return owner;
    }

    public void setOwner(Producto owner) {
        this.owner = owner;
    }
}
