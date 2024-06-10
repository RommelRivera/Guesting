/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author InfoTech
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Habitacion.findAll", query = "SELECT h FROM Habitacion h"),
    @NamedQuery(name = "Habitacion.findByIdHabitacion", query = "SELECT h FROM Habitacion h WHERE h.idHabitacion = :idHabitacion"),
    @NamedQuery(name = "Habitacion.findByNumHabitacion", query = "SELECT h FROM Habitacion h WHERE h.numHabitacion = :numHabitacion")})
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_habitacion")
    private Integer idHabitacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "num_habitacion")
    private String numHabitacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habitacion")
    private List<Reservacion> reservacionList;
    @JoinColumn(name = "precio_habitacion", referencedColumnName = "id_precio")
    @ManyToOne(optional = false)
    private PrecioHabitacion precioHabitacion;
    @JoinColumn(name = "tipo_habitacion", referencedColumnName = "id_tipo")
    @ManyToOne(cascade = CascadeType.ALL)
    private TipoHabitacion tipoHabitacion;

    public Habitacion() {
    }

    public Habitacion(String numHabitacion, PrecioHabitacion precioHabitacion) {
        this.numHabitacion = numHabitacion;
        this.precioHabitacion = precioHabitacion;
    }

    public Habitacion(String numHabitacion, PrecioHabitacion precioHabitacion, TipoHabitacion tipoHabitacion) {
        this.numHabitacion = numHabitacion;
        this.precioHabitacion = precioHabitacion;
        this.tipoHabitacion = tipoHabitacion;
    }

    public Habitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Habitacion(Integer idHabitacion, String numHabitacion, PrecioHabitacion precioHabitacion) {
        this.idHabitacion = idHabitacion;
        this.numHabitacion = numHabitacion;
        this.precioHabitacion = precioHabitacion;
    }

    public Habitacion(Integer idHabitacion, String numHabitacion) {
        this.idHabitacion = idHabitacion;
        this.numHabitacion = numHabitacion;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public List<Reservacion> getReservacionList() {
        return reservacionList;
    }

    public void setReservacionList(List<Reservacion> reservacionList) {
        this.reservacionList = reservacionList;
    }

    public PrecioHabitacion getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(PrecioHabitacion precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHabitacion != null ? idHabitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habitacion)) {
            return false;
        }
        Habitacion other = (Habitacion) object;
        if ((this.idHabitacion == null && other.idHabitacion != null) || (this.idHabitacion != null && !this.idHabitacion.equals(other.idHabitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "idHabitacion=" + idHabitacion + ", numHabitacion=" + numHabitacion + ", precioHabitacion=" + precioHabitacion + ", tipoHabitacion=" + tipoHabitacion + '}';
    }

       
}
