/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.servicio;


import com.infotech.domain.Habitacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author InfoTech
 */
@Local
public interface HabitacionService {
    public List<Habitacion> listarHabitaciones();
    
    public Habitacion encontrarHabitacionPorId(Habitacion habitacion);
    
    public List<Habitacion> encontrarHabitacionPorNumHabitacion(String numHabitacion);
    
    public List<Habitacion> encontrarHabitacionPorTipoHabitacion(String nombreHabitacion);
         
    public void registrarHabitacion(Habitacion habitacion);
    
    public void modificarHabitacion (Habitacion habitacion);
    
    public void eliminarHabitacion (Habitacion habitacion);
}

