package com.example.ant_track_sboot.modelo;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 250)
    private String descripcion;

    @Column(nullable = false)
    private Double presupuestoMaximoMensual;

    @Column(nullable = false)
    private double gastoMensual;

    @Column(nullable = false)
    private boolean esNecesaria;

    @Column(nullable = false)
    private boolean alertaActiva;

    @Column(nullable = false)
    private int prioridad;

    @Column(nullable = false)
    private boolean activa;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "categoria")
    private List<Gasto> gastos;


    public Categoria() {
    }

    public Categoria(String nombre, String descripcion, double presupuestoMaximoMensual,
            boolean esNecesaria, Integer prioridad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.presupuestoMaximoMensual = presupuestoMaximoMensual;
        this.esNecesaria = esNecesaria;
        this.prioridad = 1;
        this.gastoMensual = 0;
        this.alertaActiva = false;
        this.activa = true;
        this.fechaCreacion = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPresupuestoMaximoMensual() {
        return presupuestoMaximoMensual;
    }

    public void setPresupuestoMaximoMensual(Double presupuestoMaximoMensual) {
        this.presupuestoMaximoMensual = presupuestoMaximoMensual;
    }

    public double getGastoMensual() {
        return gastoMensual;
    }

    public void setGastoMensual(double gastoMensual) {
        this.gastoMensual = gastoMensual;
    }

    public boolean isEsNecesaria() {
        return esNecesaria;
    }

    public void setEsNecesaria(boolean esNecesaria) {
        this.esNecesaria = esNecesaria;
    }

    public boolean isAlertaActiva() {
        return alertaActiva;
    }

    public void setAlertaActiva(boolean alertaActiva) {
        this.alertaActiva = alertaActiva;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }
}