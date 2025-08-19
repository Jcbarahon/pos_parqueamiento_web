package com.parqueo.pos_parqueo_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueo.pos_parqueo_web.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // No necesitas métodos personalizados por ahora
}