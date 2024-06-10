/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.web;

import com.infotech.domain.Cliente;
import com.infotech.domain.Habitacion;
import com.infotech.domain.Reservacion;
import com.infotech.domain.TipoHabitacion;
import com.infotech.servicio.TipoHabitacionService;
import com.infotech.servicio.ReservacionService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.*;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author InfoTech
 */
@Named("reservacionBean")
@RequestScoped
public class ReservacionBean {

    @Inject
    private ReservacionService reservacionService;

    @Inject
    private TipoHabitacionService tipoHabitacionService;

    private Reservacion reservacionSeleccionada;

    private Cliente clienteSeleccionado;
    private Habitacion habitacionSeleccionada;
    private TipoHabitacion tipoHabitacionSeleccionada;
    private String nombre;
    private Date fecha;

    List<Reservacion> reservaciones;

    List<Reservacion> reservacionesFiltradas;

    @PersistenceContext(unitName = "sgPU")
    EntityManager em;

    public ReservacionBean() {
    }

    @PostConstruct

    public void inicializar() {
        this.reservaciones = reservacionService.listarReservaciones();
        this.reservacionSeleccionada = new Reservacion();
        this.clienteSeleccionado = new Cliente();
        this.habitacionSeleccionada = new Habitacion();
        this.tipoHabitacionSeleccionada = new TipoHabitacion();
    }

    public void editListener(RowEditEvent event) {
        Reservacion reservacion = (Reservacion) event.getObject();

        if (validarReservacion()) {
            reservacion.setCliente(clienteSeleccionado);
            reservacion.setHabitacion(habitacionSeleccionada);

            long difMs = Math.abs(reservacion.getFechaSalida().getTime() - reservacion.getFechaEntrada().getTime());
            int noches = (int) TimeUnit.DAYS.convert(difMs, TimeUnit.MILLISECONDS);
            
            if (reservacion.getPersonas() <= 0) {
                reservacion.setPersonas(1);
            }

            reservacion.setTotal(((reservacion.getPersonas() - 1) * 27) + (habitacionSeleccionada.getPrecioHabitacion().getPrecio() * noches));
            reservacionService.modificarReservacion(reservacion);
        }
    }

    public Reservacion getReservacionSeleccionado() {
        return reservacionSeleccionada;
    }

    public void setReservacionSeleccionado(Reservacion reservacionSeleccionada) {
        this.reservacionSeleccionada = reservacionSeleccionada;
    }

    public Habitacion getHabitacionSeleccionada() {
        return habitacionSeleccionada;
    }

    public void setHabitacionSeleccionada(Habitacion habitacionSeleccionada) {
        this.habitacionSeleccionada = habitacionSeleccionada;
    }

    public TipoHabitacion getTipoHabitacionSeleccionada() {
        return tipoHabitacionSeleccionada;
    }

    public void setTipoHabitacionSeleccionada(TipoHabitacion tipoHabitacionSeleccionada) {
        this.tipoHabitacionSeleccionada = tipoHabitacionSeleccionada;
    }

    public List<String> getNombreHabitacion() {
        return tipoHabitacionService.listarNombreHabitaciones();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public List<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public List<Reservacion> getReservacionesFiltradas() {
        return reservacionesFiltradas;
    }

    public void setReservacionesFiltradas(List<Reservacion> reservacionesFiltradas) {
        this.reservacionesFiltradas = reservacionesFiltradas;
    }

    public void errorReservacion(String error) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", error);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public boolean validarReservacion() {
        try {
            if (clienteSeleccionado.getDocIdentidad() == null) {
                Query clienteQuery = em.createQuery("from Cliente c where c.docIdentidad = :docIdentidad");
                clienteQuery.setParameter("docIdentidad", reservacionSeleccionada.getCliente().getDocIdentidad());
                clienteSeleccionado = (Cliente) clienteQuery.getSingleResult();
            } else {
                Query clienteQuery = em.createQuery("from Cliente c where c.docIdentidad = :docIdentidad");
                clienteQuery.setParameter("docIdentidad", clienteSeleccionado.getDocIdentidad());
                clienteSeleccionado = (Cliente) clienteQuery.getSingleResult();
            }
        } catch (Exception e) {
            errorReservacion("No existen clientes con ese documento de identidad");
            return false;
        }

        Query tipoHabitacionQuery = em.createQuery("from TipoHabitacion th where th.nombreHabitacion = :nombreHabitacion");
        tipoHabitacionQuery.setParameter("nombreHabitacion", nombre);
        tipoHabitacionSeleccionada = (TipoHabitacion) tipoHabitacionQuery.getSingleResult();

        
        try {
            if (habitacionSeleccionada.getNumHabitacion() == null) {
                Query habitacionQuery = em.createQuery("from Habitacion h where h.numHabitacion = :numHabitacion and h.tipoHabitacion = (select th from TipoHabitacion th where th.nombreHabitacion = :nombreHabitacion)");
                habitacionQuery.setParameter("numHabitacion", reservacionSeleccionada.getHabitacion().getNumHabitacion());
                habitacionQuery.setParameter("nombreHabitacion", tipoHabitacionSeleccionada.getNombreHabitacion());
                habitacionSeleccionada = (Habitacion) habitacionQuery.getSingleResult();
            } else {                
                Query habitacionQuery = em.createQuery("from Habitacion h where h.numHabitacion = :numHabitacion and h.tipoHabitacion = :tipoHabitacion");
                habitacionQuery.setParameter("numHabitacion", habitacionSeleccionada.getNumHabitacion());
                habitacionQuery.setParameter("tipoHabitacion", tipoHabitacionSeleccionada);
                habitacionSeleccionada = (Habitacion) habitacionQuery.getSingleResult();
            }
        } catch (Exception e) {
            errorReservacion("No existen habitaciones con ese nombre/servicio");
            return false;
        }
        
        if (reservacionSeleccionada.getFechaEntrada().after(reservacionSeleccionada.getFechaSalida()) || reservacionSeleccionada.getFechaEntrada().equals(reservacionSeleccionada.getFechaSalida())) {
            errorReservacion("La fecha de entrada no puede ser mayor o igual a la fecha de salida");
            return false;
        }

        for (Reservacion room : reservaciones) {
            if (room.getHabitacion().getNumHabitacion().equals(habitacionSeleccionada.getNumHabitacion())) {
                if ((room.getFechaEntrada().before(reservacionSeleccionada.getFechaEntrada()) || room.getFechaEntrada().equals(reservacionSeleccionada.getFechaEntrada()))
                        && room.getFechaSalida().after(reservacionSeleccionada.getFechaEntrada())) {
                    errorReservacion("La habitación no está disponible para esta fecha de entrada");
                    return false;
                }
            }
        }

        for (Reservacion room : reservaciones) {
            if (room.getHabitacion().getNumHabitacion().equals(habitacionSeleccionada.getNumHabitacion())) {
                if (reservacionSeleccionada.getFechaEntrada().before(room.getFechaEntrada()) && reservacionSeleccionada.getFechaSalida().after(room.getFechaEntrada())) {
                    errorReservacion("La habitación no está disponible para esta fecha de salida");
                    return false;
                }
            }
        }

        return true;
    }

    public void agregarReservacion() {
        if (validarReservacion()) {
            this.reservacionSeleccionada.setCliente(clienteSeleccionado);
            this.reservacionSeleccionada.setHabitacion(habitacionSeleccionada);

            long difMs = Math.abs(this.reservacionSeleccionada.getFechaSalida().getTime() - this.reservacionSeleccionada.getFechaEntrada().getTime());
            int noches = (int) TimeUnit.DAYS.convert(difMs, TimeUnit.MILLISECONDS);
            
            if (this.reservacionSeleccionada.getPersonas() <= 0) {
                this.reservacionSeleccionada.setPersonas(1);
            }

            this.reservacionSeleccionada.setTotal(((this.reservacionSeleccionada.getPersonas() - 1) * 27) + (habitacionSeleccionada.getPrecioHabitacion().getPrecio() * noches));
            try{
                this.reservacionService.registrarReservacion(reservacionSeleccionada);
            }catch(Exception e){
                //System.out.println(e.getLocalizedMessage());
            }
            this.reservaciones.add(reservacionSeleccionada);
            this.reservacionSeleccionada = null;
            
            try {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/faces/listarReservaciones.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ReservacionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void eliminarReservacion() {
        this.reservacionService.eliminarReservacion(reservacionSeleccionada);
        this.reservaciones.remove(reservacionSeleccionada);
        this.reservacionSeleccionada = null;
    }

    public void reiniciarReservacionSeleccionado() {
        this.reservacionSeleccionada = new Reservacion();
    }
}
