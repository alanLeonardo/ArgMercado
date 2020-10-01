package org.example.ArgMercado.dao;

import org.example.ArgMercado.modelo.Categoria;
import org.example.ArgMercado.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IProductoDAO extends JpaRepository<Producto,Integer> {

}
