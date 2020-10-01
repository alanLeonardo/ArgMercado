package org.example.ArgMercado.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @NotBlank(message = "El nombre no puede ser un campo vacio")
    private String nombreCompleto;

    @Column(unique=true)
    @Email(message = "Email no es valido tiene que tener un formato, por ejemplo: nombreEmail@gmail.com")
    private String email;

    @NotBlank(message = "La contraseña no puede ser un campo vacio")
    private String contraseña;

    @OneToMany(mappedBy="owner", cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Producto> productosComprados = new ArrayList<Producto>();

    public Usuario() {

    }

    public Usuario(@NotBlank(message = "El nombre no puede ser un campo vacio") String nombreCompleto, @Email(message = "Email no es valido tiene que tener un formato, por ejemplo: nombreEmail@gmail.com") String email, @NotBlank(message = "La contraseña no puede ser un campo vacio") String contraseña) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<Producto> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(List<Producto> productosComprados) {
        this.productosComprados = productosComprados;
    }

    public void comprar(Producto producto) {
        this.productosComprados.add(producto);
    }

    public Producto getProducto(int idProducto) {
        return this.productosComprados.stream().filter(pro -> pro.getIdProducto() == idProducto).findFirst().get();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
