package com.parqueo.pos_parqueo_web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_espacio")
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspacio;

    private String zona;

    // Constructor vacío (necesario para JPA)
    public Espacio() {
    }

    // Constructor con parámetros (para usar en simulaciones)
    public Espacio(Integer idEspacio, String zona) {
        this.idEspacio = idEspacio;
        this.zona = zona;
    }

    // Getters y Setters
    public Integer getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(Integer idEspacio) {
        this.idEspacio = idEspacio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}