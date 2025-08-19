package com.parqueo.pos_parqueo_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;

    @Column(name = "Nombre_Cliente")
    private String nombreCliente;

    @Column(name = "Apellido_Cliente")
    private String apellidoCliente;

    @Column(name = "razon_s_Cliente")
    private String razonSCliente;

    @Column(name = "ruc_Cliente")
    private String rucCliente;

    @Column(name = "direccion_Cliente")
    private String direccionCliente;

    @Column(name = "telefono_Cliente")
    private String telefonoCliente;

    @Column(name = "correo_Cliente")
    private String correoCliente;

    // === Getters y Setters ===

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getApellidoCliente() { return apellidoCliente; }
    public void setApellidoCliente(String apellidoCliente) { this.apellidoCliente = apellidoCliente; }

    public String getRazonSCliente() { return razonSCliente; }
    public void setRazonSCliente(String razonSCliente) { this.razonSCliente = razonSCliente; }

    public String getRucCliente() { return rucCliente; }
    public void setRucCliente(String rucCliente) { this.rucCliente = rucCliente; }

    public String getDireccionCliente() { return direccionCliente; }
    public void setDireccionCliente(String direccionCliente) { this.direccionCliente = direccionCliente; }

    public String getTelefonoCliente() { return telefonoCliente; }
    public void setTelefonoCliente(String telefonoCliente) { this.telefonoCliente = telefonoCliente; }

    public String getCorreoCliente() { return correoCliente; }
    public void setCorreoCliente(String correoCliente) { this.correoCliente = correoCliente; }
}