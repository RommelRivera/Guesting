/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.servicio;


import com.infotech.domain.Habitacion;
import com.infotech.domain.Reservacion;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author InfoTech
 */
@Local
public interface ReservacionService {
    public List<Reservacion> listarReservaciones();
    
    public Reservacion encontrarReservacionPorId(Reservacion reservacion);
    
    public List<Reservacion> encontrarReservacionPorFechaEntrada(Date fechaEntrada);
    
    public List<Reservacion> encontrarReservacionPorFechaSalida(Date fechaSalida);
    
    public List<Reservacion> encontrarReservacionPorCliente(String docIdentidad);
    
    public List<Reservacion> encontrarReservacionPorHabitacion(Habitacion habitacion); 
     
    public void registrarReservacion(Reservacion reservacion);
    
    public void modificarReservacion (Reservacion reservacion);
    
    public void eliminarReservacion (Reservacion reservacion);
}

