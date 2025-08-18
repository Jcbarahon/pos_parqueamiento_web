package com.parqueo.pos_parqueo_web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parqueo.pos_parqueo_web.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    Optional<Usuario> findByUsuario(String usuario);
}