package org.example.ArgMercado.modelo;

import org.example.ArgMercado.enums.Categoria;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto implements Serializable,Comparable<Producto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Categoria categoria;

    @OneToMany(mappedBy="owner", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImagenProducto> imagenes = new ArrayList<ImagenProducto>();

    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @Min(1)
    private Double precio = 0.0;

    private int stock = 0;

    public Producto() {

    }

    public Producto(int idProducto, @NotNull Categoria categoria, @NotBlank String titulo, @NotBlank String descripcion, @Min(1) Double precio, int stock) {
        this.idProducto = idProducto;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(int idProducto, @NotNull Categoria categoria, List<ImagenProducto> imagenes, @NotBlank String titulo, @NotBlank String descripcion, @Min(1) Double precio, int stock) {
        this.idProducto = idProducto;
        this.categoria = categoria;
        this.imagenes = imagenes;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setImagen(ImagenProducto imagen) {
        this.imagenes.add(imagen);
    }

    public void setImagenes(List<ImagenProducto> imagenes) {
        this.imagenes = imagenes;
    }

    public List<ImagenProducto> getImagenes() {
        return imagenes;
    }

    @Override
    public int compareTo(Producto producto) {
        if((this.getPrecio() > producto.getPrecio())) {
            return 1;
        } else { return 0;}
    }
}