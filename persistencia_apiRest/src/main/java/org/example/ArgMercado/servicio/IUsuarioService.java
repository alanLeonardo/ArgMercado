package org.example.ArgMercado.servicio;

import org.example.ArgMercado.modelo.Usuario;

import java.util.List;

public interface IUsuarioService {

    public void guardar(Usuario usuario);

    public Usuario recuperar(int idUsuario);

    public List<Usuario> recuperarTodo();

    public void borrarTodo();

    public Boolean login(String email, String contraseña);

}
