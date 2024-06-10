/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.PrecioHabitacion;
import java.util.List;

/**
 *
 * @author InfoTech
 */
public interface PrecioHabitacionDao {
    public List<PrecioHabitacion> findAllPrecioHabitaciones();
    
    public PrecioHabitacion findPrecioHabitacionById(PrecioHabitacion precioHabitacion);
    
    public List<PrecioHabitacion> findPrecioHabitacionByPrecio(int precio);
    
    public void insertPrecioHabitacion(PrecioHabitacion precioHabitacion);
    
    public void updatePrecioHabitacion(PrecioHabitacion precioHabitacion);
    
    public void deletePrecioHabitacion(PrecioHabitacion precioHabitacion);
}
