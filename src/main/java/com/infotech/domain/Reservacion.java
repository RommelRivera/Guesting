/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author InfoTech
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Reservacion.findAll", query = "SELECT r FROM Reservacion r"),
    @NamedQuery(name = "Reservacion.findByIdReservacion", query = "SELECT r FROM Reservacion r WHERE r.idReservacion = :idReservacion"),
    @NamedQuery(name = "Reservacion.findByFechaEntrada", query = "SELECT r FROM Reservacion r WHERE r.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "Reservacion.findByFechaSalida", query = "SELECT r FROM Reservacion r WHERE r.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Reservacion.findByPersonas", query = "SELECT r FROM Reservacion r WHERE r.personas = :personas"),
    @NamedQuery(name = "Reservacion.findByTotal", query = "SELECT r FROM Reservacion r WHERE r.total = :total")})
public class Reservacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reservacion")
    private Integer idReservacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    private int personas;
    @Basic(optional = false)
    @NotNull
    private int total;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "id_habitacion", referencedColumnName = "id_habitacion")
    @ManyToOne(cascade = CascadeType.ALL)
    private Habitacion habitacion;

    public Reservacion() {
    }

    public Reservacion(Date fechaEntrada, Date fechaSalida, int personas, int total) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.personas = personas;
        this.total = total;
    }

    public Reservacion(Date fechaEntrada, Date fechaSalida, int personas, int total, Cliente cliente) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.personas = personas;
        this.total = total;
        this.cliente = cliente;
    }

    public Reservacion(Date fechaEntrada, Date fechaSalida, int personas, int total, Habitacion habitacion) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.personas = personas;
        this.total = total;
        this.habitacion = habitacion;
    }

    public Reservacion(Date fechaEntrada, Date fechaSalida, int personas, int total, Cliente cliente, Habitacion habitacion) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.personas = personas;
        this.total = total;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    public Reservacion(Integer idReservacion) {
        this.idReservacion = idReservacion;
    }

    public Reservacion(Integer idReservacion, Date fechaEntrada, Date fechaSalida, int personas, int total) {
        this.idReservacion = idReservacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.personas = personas;
        this.total = total;
    }

    public Integer getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(Integer idReservacion) {
        this.idReservacion = idReservacion;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReservacion != null ? idReservacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservacion)) {
            return false;
        }
        Reservacion other = (Reservacion) object;
        if ((this.idReservacion == null && other.idReservacion != null) || (this.idReservacion != null && !this.idReservacion.equals(other.idReservacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservacion{" + "idReservacion=" + idReservacion + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", personas=" + personas + ", total=" + total + ", cliente=" + cliente + ", habitacion=" + habitacion + '}';
    }

        
}
