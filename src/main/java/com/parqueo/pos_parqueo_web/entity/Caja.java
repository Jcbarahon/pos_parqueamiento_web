package com.parqueo.pos_parqueo_web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_cajas")
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_table_cajas;

    private java.sql.Date fecha;
    private Float monto;
    private String estado; // "abierta", "cerrada"

    // Getters y Setters
    public Integer getId_table_cajas() {
        return id_table_cajas;
    }

    public void setId_table_cajas(Integer id_table_cajas) {
        this.id_table_cajas = id_table_cajas;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}