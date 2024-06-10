/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.domain;

import java.util.Date;

/**
 *
 * @author InfoTech
 */
public class ReservacionReporte {
    private Reservacion reservacion;
    private Cliente cliente;
    private Habitacion habitacion;
    
    public ReservacionReporte(){
        
    }
    
    public ReservacionReporte(Reservacion reservacion, Cliente cliente, Habitacion habitacion){
        this.reservacion = reservacion;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }
    
    public int getIdReservacion(){
        return reservacion.getIdReservacion();
    }
    
    public Date getFechaEntrada(){
        return reservacion.getFechaEntrada();
    }
    
    public Date getFechaSalida(){
        return reservacion.getFechaSalida();
    }
    
    public int getPersonas(){
        return reservacion.getPersonas();
    }
    
    public int getTotal(){
        return reservacion.getTotal();
    }
    
    public int getIdCliente(){
        return cliente.getIdCliente();
    }
    
    public String getDocIdentidad(){
        return cliente.getDocIdentidad();
    }
    
    public int getIdHabitacion(){
        return habitacion.getIdHabitacion();
    }
    
    public String getNumHabitacion(){
        return habitacion.getNumHabitacion();
    }
    
    public int getIdTipo(){
        return habitacion.getTipoHabitacion().getIdTipo();
    }
    
    public String getNombreHabitacion(){
        return habitacion.getTipoHabitacion().getNombreHabitacion();
    }
}
