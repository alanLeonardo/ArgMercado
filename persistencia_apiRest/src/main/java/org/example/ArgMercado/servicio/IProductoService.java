package org.example.ArgMercado.servicio;

import org.example.ArgMercado.modelo.Categoria;
import org.example.ArgMercado.modelo.Producto;

import java.util.List;

public interface IProductoService {

    public void guardar(Producto producto);

    public Producto recuperar(int idProducto);

    public List<Producto> recuperarTodo();

    public void borrarTodo();

    public List<Producto> recuperarProductosDeMenorAMayor();

    List<Producto> recuperarProductosDeMayorAMenor();

    void borrarProducto(Producto producto);

    boolean contains(Producto producto);


}
