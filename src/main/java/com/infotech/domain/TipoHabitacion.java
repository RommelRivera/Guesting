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
@Table(name = "tipo_habitacion")
@NamedQueries({
    @NamedQuery(name = "TipoHabitacion.findAll", query = "SELECT t FROM TipoHabitacion t"),
    @NamedQuery(name = "TipoHabitacion.findByIdTipo", query = "SELECT t FROM TipoHabitacion t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TipoHabitacion.findByNombreHabitacion", query = "SELECT t FROM TipoHabitacion t WHERE t.nombreHabitacion = :nombreHabitacion")})
public class TipoHabitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_habitacion")
    private String nombreHabitacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoHabitacion")
    private List<Habitacion> habitacionList;

    public TipoHabitacion() {
    }

    public TipoHabitacion(String nombreHabitacion) {
        this.nombreHabitacion = nombreHabitacion;
    }

    public TipoHabitacion(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public TipoHabitacion(Integer idTipo, String nombreHabitacion) {
        this.idTipo = idTipo;
        this.nombreHabitacion = nombreHabitacion;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreHabitacion() {
        return nombreHabitacion;
    }

    public void setNombreHabitacion(String nombreHabitacion) {
        this.nombreHabitacion = nombreHabitacion;
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
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoHabitacion)) {
            return false;
        }
        TipoHabitacion other = (TipoHabitacion) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoHabitacion{" + "idTipo=" + idTipo + ", nombreHabitacion=" + nombreHabitacion + '}';
    }

        
}
