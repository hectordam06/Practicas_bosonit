package com.virtualtravel.common;

import java.util.Date;


public class Autobus {
    private int id;
    private String destino;
    private Date fecha;
    private double hora;
    private Integer capacidad;
    public Autobus() {}


    public Autobus(int id, String destino, Date fecha, double hora, Integer capacidad) {
        this.id = id;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getHora() {
        return hora;
    }

    public void setHora(double hora) {
        this.hora = hora;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
