/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.servicio;


import com.infotech.domain.PrecioHabitacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author InfoTech
 */
@Local
public interface PrecioHabitacionService {
    public List<PrecioHabitacion> listarPrecioHabitaciones();
    
    public PrecioHabitacion encontrarPrecioHabitacionPorId(PrecioHabitacion precioHabitacion);
    
    public List<PrecioHabitacion> encontrarPrecioHabitacionPorPrecio(int precio);
    
    public void registrarPrecioHabitacion(PrecioHabitacion precioHabitacion);
    
    public void modificarPrecioHabitacion (PrecioHabitacion precioHabitacion);
    
    public void eliminarPrecioHabitacion (PrecioHabitacion precioHabitacion);
}

