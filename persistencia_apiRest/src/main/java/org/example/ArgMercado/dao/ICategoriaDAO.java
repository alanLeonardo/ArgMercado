package org.example.ArgMercado.dao;

import org.example.ArgMercado.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaDAO extends JpaRepository<Categoria,Integer> {
}
