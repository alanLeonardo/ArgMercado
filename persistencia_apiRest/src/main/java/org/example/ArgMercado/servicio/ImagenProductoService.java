package org.example.ArgMercado.servicio;

import org.example.ArgMercado.dao.IImagenProductoDAO;
import org.example.ArgMercado.modelo.ImagenProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenProductoService implements IImagenProductoService {

    @Autowired(required=true)
    private IImagenProductoDAO iImagenProductoDAO;


    @Override
    public void guardar(ImagenProducto imagenProducto) {
         this.iImagenProductoDAO.save(imagenProducto);
    }

    @Override
    public ImagenProducto recuperar(int idImagenProducto) {
        return this.iImagenProductoDAO.findById(idImagenProducto).get();
    }

    @Override
    public List<ImagenProducto> recuperarTodo() {
        return this.iImagenProductoDAO.findAll();
    }

    @Override
    public void borrarTodo() {
       this.iImagenProductoDAO.deleteAll();
    }
}
