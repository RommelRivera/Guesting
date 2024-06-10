/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.web;

import com.infotech.domain.TipoHabitacion;
import com.infotech.servicio.TipoHabitacionService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author InfoTech
 */

@Named ("tipoHabitacionBean")
@RequestScoped
public class TipoHabitacionBean {
    @Inject
    private TipoHabitacionService tipoHabitacionService;
    
    private TipoHabitacion tipoHabitacionSeleccionado;
    
    List<TipoHabitacion> tipoHabitaciones;
    
    public TipoHabitacionBean() {
    }
    
    @PostConstruct
    
    public void inicializar() {
        this.tipoHabitaciones = tipoHabitacionService.listarTipoHabitaciones();
        this.tipoHabitacionSeleccionado = new TipoHabitacion();
    }
    
    public void editListener(RowEditEvent event) {
        TipoHabitacion tipoHabitacion = (TipoHabitacion) event.getObject();
        tipoHabitacionService.modificarTipoHabitacion(tipoHabitacion);
    }

    public TipoHabitacion getTipoHabitacionSeleccionado() {
        return tipoHabitacionSeleccionado;
    }

    public void setTipoHabitacionSeleccionado(TipoHabitacion tipoHabitacionSeleccionado) {
        this.tipoHabitacionSeleccionado = tipoHabitacionSeleccionado;
    }
    
    public String getNombreHabitacion() {
        return tipoHabitacionSeleccionado.getNombreHabitacion();
    }
    
    public void setNombreHabitacion(String nombreHabitacion) {
        this.tipoHabitacionSeleccionado.setNombreHabitacion(nombreHabitacion);
    }

    public List<TipoHabitacion> getTipoHabitaciones() {
        return tipoHabitaciones;
    }

    public void setTipoHabitaciones(List<TipoHabitacion> tipoHabitaciones) {
        this.tipoHabitaciones = tipoHabitaciones;
    }
    

    public void eliminarTipoHabitacion() {
        this.tipoHabitacionService.eliminarTipoHabitacion(tipoHabitacionSeleccionado);
        this.tipoHabitaciones.remove(tipoHabitacionSeleccionado);
        this.tipoHabitacionSeleccionado = null;
    }
    
    public void reiniciarTipoHabitacionSeleccionado() {
        this.tipoHabitacionSeleccionado = new TipoHabitacion();
    }
}
