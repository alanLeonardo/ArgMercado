package org.example.ArgMercado.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto implements Comparable<Producto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Categoria categoria;

    @OneToMany(mappedBy="owner", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ImagenProducto> imagenes = new ArrayList<ImagenProducto>();

    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @Min(1)
    private Double precio = 0.0;

    private int stock = 0;

    @ManyToOne
    @JsonBackReference
    private Usuario owner;


    public Producto(@NotBlank String titulo) {
       this.titulo = titulo;
    }

    public Producto() {

    }

    public Producto(@NotNull Categoria categoria, List<ImagenProducto> imagenes, @NotBlank String titulo, @NotBlank String descripcion, @Min(1) Double precio, int stock) {
        this.categoria = categoria;
        this.imagenes = imagenes;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdProducto() {
        return this.idProducto;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public int getStock() {
        return this.stock;
    }

    public Usuario getOwner() {
        return this.owner;
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
        return this.imagenes;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(Producto producto) {
        if((this.getPrecio() > producto.getPrecio())) {
            return 1;
        } else { return 0;}
    }

    public void disminuirStock() {
        this.stock = stock - 1;
    }
}