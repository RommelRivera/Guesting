/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.Habitacion;
import java.util.List;

/**
 *
 * @author InfoTech
 */
public interface HabitacionDao {
    public List<Habitacion> findAllHabitaciones();
    
    public Habitacion findHabitacionById(Habitacion habitacion);
    
    public List<Habitacion> findHabitacionByNumHabitacion(String numHabitacion);
    
    public List<Habitacion> findHabitacionByTipoHabitacion(String nombreHabitacion);
    
    public void insertHabitacion(Habitacion habitacion);
    
    public void updateHabitacion(Habitacion habitacion);
    
    public void deleteHabitacion(Habitacion habitacion);
}
