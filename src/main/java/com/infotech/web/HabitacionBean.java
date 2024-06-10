/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.web;

import com.infotech.domain.Habitacion;
import com.infotech.domain.TipoHabitacion;
import com.infotech.servicio.HabitacionService;
import com.infotech.servicio.TipoHabitacionService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author InfoTech
 */

@Named ("habitacionBean")
@RequestScoped
public class HabitacionBean {
    @Inject
    private HabitacionService habitacionService;
    
    @Inject
    private TipoHabitacionService tipoHabitacionService;
    
    private Habitacion habitacionSeleccionado;
    private TipoHabitacion tipoHabitacionSeleccionada;
    private String nombre;
    
    List<Habitacion> habitaciones;
    List<TipoHabitacion> tipoHabitaciones;
    
    List<Habitacion> habitacionesFiltradas;
    
    EntityManager em;
    
    public HabitacionBean() {
    }
    
    @PostConstruct
    
    public void inicializar() {
        this.habitaciones = habitacionService.listarHabitaciones();
        this.habitacionSeleccionado = new Habitacion();
        this.tipoHabitacionSeleccionada = new TipoHabitacion();
        this.tipoHabitaciones = tipoHabitacionService.listarTipoHabitaciones();
    }

    public List<TipoHabitacion> getTipoHabitaciones() {
        return tipoHabitaciones;
    }

    public void setTipoHabitaciones(List<TipoHabitacion> tipoHabitaciones) {
        this.tipoHabitaciones = tipoHabitaciones;
    }
    
    public void editListener(RowEditEvent event) {
        Habitacion habitacion = (Habitacion) event.getObject();
        
        /*Query query = em.createQuery("from TipoHabitacion th where th.nombreHabitacion =: nombreHabitacion");
        query.setParameter("nombreHabitacion", nombreHabitacion);
        habitacion.setTipoHabitacion((TipoHabitacion) query.getSingleResult());*/
        
        //habitacion.setTipoHabitacion(tipoHabitacionSeleccionada);
        //habitacionService.modificarHabitacion(habitacion);
        System.out.println(habitacion.toString());
        System.out.println(this.tipoHabitacionSeleccionada.getNombreHabitacion());
        System.out.println(event.getObject().toString());
    }
    
    public List<String> getNombreHabitacion() {
        return tipoHabitacionService.listarNombreHabitaciones();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.tipoHabitacionSeleccionada.setNombreHabitacion(nombre);
    }

    public Habitacion getHabitacionSeleccionado() {
        return habitacionSeleccionado;
    }

    public void setHabitacionSeleccionado(Habitacion habitacionSeleccionado) {
        this.habitacionSeleccionado = habitacionSeleccionado;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public List<Habitacion> getHabitacionesFiltradas() {
        return habitacionesFiltradas;
    }

    public void setHabitacionesFiltradas(List<Habitacion> habitacionesFiltradas) {
        this.habitacionesFiltradas = habitacionesFiltradas;
    }

    public void eliminarHabitacion() {
        this.habitacionService.eliminarHabitacion(habitacionSeleccionado);
        this.habitaciones.remove(habitacionSeleccionado);
        this.habitacionSeleccionado = null;
    }
    
    public void reiniciarHabitacionSeleccionado() {
        this.habitacionSeleccionado = new Habitacion();
    }
}
