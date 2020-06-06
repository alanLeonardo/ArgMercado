package org.example.ArgMercado.servicio;

import org.example.ArgMercado.modelo.ImagenProducto;

import java.util.List;

public interface IImagenProductoService {

    public void guardar(ImagenProducto imagenProducto);

    public ImagenProducto recuperar(int idImagenProducto);

    public List<ImagenProducto> recuperarTodo();

    public void borrarTodo();
}
