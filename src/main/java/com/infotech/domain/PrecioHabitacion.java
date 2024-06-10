/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author InfoTech
 */
@Entity
@Table(name = "precio_habitacion")
@NamedQueries({
    @NamedQuery(name = "PrecioHabitacion.findAll", query = "SELECT p FROM PrecioHabitacion p"),
    @NamedQuery(name = "PrecioHabitacion.findByIdPrecio", query = "SELECT p FROM PrecioHabitacion p WHERE p.idPrecio = :idPrecio"),
    @NamedQuery(name = "PrecioHabitacion.findByPrecio", query = "SELECT p FROM PrecioHabitacion p WHERE p.precio = :precio")})
public class PrecioHabitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_precio")
    private Integer idPrecio;
    @Basic(optional = false)
    @NotNull
    private int precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "precioHabitacion")
    private List<Habitacion> habitacionList;

    public PrecioHabitacion() {
    }

    public PrecioHabitacion(int precio) {
        this.precio = precio;
    }

    public PrecioHabitacion(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public PrecioHabitacion(Integer idPrecio, int precio) {
        this.idPrecio = idPrecio;
        this.precio = precio;
    }

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public List<Habitacion> getHabitacionList() {
        return habitacionList;
    }

    public void setHabitacionList(List<Habitacion> habitacionList) {
        this.habitacionList = habitacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrecio != null ? idPrecio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecioHabitacion)) {
            return false;
        }
        PrecioHabitacion other = (PrecioHabitacion) object;
        if ((this.idPrecio == null && other.idPrecio != null) || (this.idPrecio != null && !this.idPrecio.equals(other.idPrecio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrecioHabitacion{" + "idPrecio=" + idPrecio + ", precio=" + precio + '}';
    }

        
}
