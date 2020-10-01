package org.example.ArgMercado.servicio;

import org.example.ArgMercado.dao.IProductoDAO;
import org.example.ArgMercado.modelo.Categoria;
import org.example.ArgMercado.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired(required=true)
    private IProductoDAO productoDAO;

    @Override
    public void guardar(Producto producto) {
         this.productoDAO.save(producto);
    }

    @Override
    public Producto recuperar(int idProducto) {
        return this.productoDAO.findById(idProducto).get();
    }

    @Override
    public List<Producto> recuperarTodo() {
        return this.productoDAO.findAll();
    }

    @Override
    public void borrarTodo() {
        this.productoDAO.deleteAll();
    }

    @Override
    public List<Producto> recuperarProductosDeMenorAMayor() {
        Sort s = Sort.by(Sort.Direction.ASC,"precio");

        return this.productoDAO.findAll(s);
    }

    @Override
    public List<Producto> recuperarProductosDeMayorAMenor() {
        Sort s = Sort.by(Sort.Direction.DESC,"precio");

        return this.productoDAO.findAll(s);
    }

    @Override
    public void borrarProducto(Producto producto) {
       this.productoDAO.delete(producto);
    }

    @Override
    public boolean contains(Producto producto) {
        return this.productoDAO.findAll().contains(producto);
    }




}
