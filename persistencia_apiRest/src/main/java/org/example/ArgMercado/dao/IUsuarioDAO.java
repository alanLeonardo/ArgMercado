package org.example.ArgMercado.dao;

import org.example.ArgMercado.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDAO extends JpaRepository<Usuario,Integer> {

    @Query("SELECT u from Usuario u WHERE u.email = :email")
    Usuario findByEmail(String email);
}
