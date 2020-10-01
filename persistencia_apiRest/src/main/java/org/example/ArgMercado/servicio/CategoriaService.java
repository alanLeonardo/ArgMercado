package org.example.ArgMercado.servicio;

import org.example.ArgMercado.dao.ICategoriaDAO;
import org.example.ArgMercado.modelo.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired(required=true)
    private ICategoriaDAO categoriaDAO;

    @Override
    public void guardar(Categoria categoria) {
       this.categoriaDAO.save(categoria);
    }

    @Override
    public Categoria recuperar(int idCategoria) {
        return this.categoriaDAO.findById(idCategoria).get();
    }

    @Override
    public List<Categoria> recuperarTodo() {
        return this.categoriaDAO.findAll();
    }

    @Override
    public void borrarTodo() {
       this.categoriaDAO.deleteAll();
    }
}
