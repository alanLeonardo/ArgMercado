package org.example.ArgMercado.servicio;

import org.example.ArgMercado.dao.IUsuarioDAO;
import org.example.ArgMercado.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired(required=true)
    private IUsuarioDAO usuarioDAO;

    @Override
    public void guardar(Usuario usuario) {
      this.usuarioDAO.save(usuario);
    }

    @Override
    public Usuario recuperar(int idUsuario) {
        return this.usuarioDAO.findById(idUsuario).get();
    }

    @Override
    public List<Usuario> recuperarTodo() {
        return this.usuarioDAO.findAll();
    }

    @Override
    public void borrarTodo() {
       this.usuarioDAO.deleteAll();
    }

    @Override
    public Boolean login(String email, String contraseña) {
        Usuario usuario = this.usuarioDAO.findByEmail(email);
        Boolean token = usuario.getContraseña().equals(contraseña);
        return token;
    }

}
