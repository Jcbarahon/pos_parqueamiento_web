package com.parqueo.pos_parqueo_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parqueo.pos_parqueo_web.entity.Espacio;

@Repository
public interface EspacioRepository extends JpaRepository<Espacio, Integer> {
    // Métodos automáticos: findAll(), deleteAll(), save(), etc.
}