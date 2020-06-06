package org.example.ArgMercado.dao;

import org.example.ArgMercado.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IProductoDAO extends JpaRepository<Producto,Integer> {
}
