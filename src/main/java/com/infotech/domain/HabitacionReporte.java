/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.domain;

/**
 *
 * @author InfoTech
 */
public class HabitacionReporte {
    private Habitacion habitacion;
    private int precio;
    
    public HabitacionReporte() {
    }
    
    public HabitacionReporte(Habitacion habitacion, int precio){
        this.habitacion = habitacion;
        this.precio = precio;
    }
    
    public int getIdHabitacion(){
        return habitacion.getIdHabitacion();
    }
    
    public String getNumHabitacion(){
        return habitacion.getNumHabitacion();
    }
    
    public int getIdPrecio(){
        return habitacion.getPrecioHabitacion().getIdPrecio();
    }
    
    public int getIdTipo(){
        return habitacion.getTipoHabitacion().getIdTipo();
    }
    
    public String getNombreHabitacion(){
        return habitacion.getTipoHabitacion().getNombreHabitacion();
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
