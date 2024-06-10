/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.Habitacion;
import com.infotech.domain.Reservacion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author InfoTech
 */
public interface ReservacionDao {
    public List<Reservacion> findAllReservaciones();
    
    public Reservacion findReservacionById(Reservacion reservacion);
    
    public List<Reservacion> findReservacionByFechaEntrada(Date fechaEntrada);
    
    public List<Reservacion> findReservacionByFechaSalida(Date fechaSalida);
    
    public List<Reservacion> findReservacionByCliente(String docIdentidad);
    
    public List<Reservacion> findReservacionByHabitacion(Habitacion habitacion);
    
    public void insertReservacion(Reservacion reservacion);
    
    public void updateReservacion(Reservacion reservacion);
    
    public void deleteReservacion(Reservacion reservacion);
}
