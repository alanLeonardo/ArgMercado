package org.example.ArgMercado.modelo;

import javax.persistence.Lob;
import java.sql.Blob;

public class ImagenWapper {

    private String nombre;

    private String tipo;

    private byte[] foto ;

    public ImagenWapper(ImagenProducto imagenProducto) {
        this.nombre = imagenProducto.getNombre();
        this.tipo = imagenProducto.getTipo();
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
