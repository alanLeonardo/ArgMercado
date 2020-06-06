package org.example.ArgMercado.servicio;

import org.example.ArgMercado.modelo.Producto;

import java.util.List;

public interface IProductoService {

    public void guardar(Producto producto);

    public Producto recuperar(int idProducto);

    public List<Producto> recuperarTodo();

    public void borrarTodo();

    public List<Producto> recuperarProductosDeMenorAMayor();
}
