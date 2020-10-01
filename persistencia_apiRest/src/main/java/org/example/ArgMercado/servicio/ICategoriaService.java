package org.example.ArgMercado.servicio;

import org.example.ArgMercado.modelo.Categoria;

import java.util.List;

public interface ICategoriaService {

    public void guardar(Categoria categoria);

    public Categoria recuperar(int idCategoria);

    public List<Categoria> recuperarTodo();

    public void borrarTodo();

}
