/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.web;

import com.infotech.domain.PrecioHabitacion;
import com.infotech.servicio.PrecioHabitacionService;
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

@Named ("precioHabitacionBean")
@RequestScoped
public class PrecioHabitacionBean {
    @Inject
    private PrecioHabitacionService precioHabitacionService;
    
    private PrecioHabitacion precioHabitacionSeleccionado;
    
    List<PrecioHabitacion> precioHabitaciones;
    
    public PrecioHabitacionBean() {
    }
    
    @PostConstruct
    
    public void inicializar() {
        this.precioHabitaciones = precioHabitacionService.listarPrecioHabitaciones();
        this.precioHabitacionSeleccionado = new PrecioHabitacion();
    }
    
    public void editListener(RowEditEvent event) {
        PrecioHabitacion precioHabitacion = (PrecioHabitacion) event.getObject();
        precioHabitacionService.modificarPrecioHabitacion(precioHabitacion);
    }

    public PrecioHabitacion getPrecioHabitacionSeleccionado() {
        return precioHabitacionSeleccionado;
    }

    public void setPrecioHabitacionSeleccionado(PrecioHabitacion precioHabitacionSeleccionado) {
        this.precioHabitacionSeleccionado = precioHabitacionSeleccionado;
    }

    public List<PrecioHabitacion> getPrecioHabitaciones() {
        return precioHabitaciones;
    }

    public void setPrecioHabitaciones(List<PrecioHabitacion> precioHabitaciones) {
        this.precioHabitaciones = precioHabitaciones;
    }
    

    public void eliminarPrecioHabitacion() {
        this.precioHabitacionService.eliminarPrecioHabitacion(precioHabitacionSeleccionado);
        this.precioHabitaciones.remove(precioHabitacionSeleccionado);
        this.precioHabitacionSeleccionado = null;
    }
    
    public void reiniciarPrecioHabitacionSeleccionado() {
        this.precioHabitacionSeleccionado = new PrecioHabitacion();
    }
}
