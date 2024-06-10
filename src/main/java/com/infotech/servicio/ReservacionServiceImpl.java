/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.servicio;

import com.infotech.datos.ReservacionDao;
import com.infotech.domain.Habitacion;
import com.infotech.domain.Reservacion;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author InfoTech
 */

@Stateless
public class ReservacionServiceImpl implements ReservacionService{

    @Inject
    private ReservacionDao reservacionDao;
    
    @Resource
    private SessionContext contexto;
    
    @Override
    public List<Reservacion> listarReservaciones() {
        return reservacionDao.findAllReservaciones();
    }

    @Override
    public Reservacion encontrarReservacionPorId(Reservacion reservacion) {
        return reservacionDao.findReservacionById(reservacion);
    }

    @Override
    public List<Reservacion> encontrarReservacionPorFechaEntrada(Date fechaEntrada) {
        return reservacionDao.findReservacionByFechaEntrada(fechaEntrada);
    }
    
     @Override
    public List<Reservacion> encontrarReservacionPorFechaSalida(Date fechaSalida) {
        return reservacionDao.findReservacionByFechaSalida(fechaSalida);
    }
    
      @Override
    public List<Reservacion> encontrarReservacionPorCliente(String docIdentidad) {
        return reservacionDao.findReservacionByCliente(docIdentidad);
    }
    
      @Override
    public List<Reservacion> encontrarReservacionPorHabitacion(Habitacion habitacion) {
        return reservacionDao.findReservacionByHabitacion(habitacion);
    }

    @Override
    public void registrarReservacion(Reservacion reservacion) {
        reservacionDao.insertReservacion(reservacion);
    }

    @Override
    public void modificarReservacion(Reservacion reservacion) {
        try {
            reservacionDao.updateReservacion(reservacion);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void eliminarReservacion(Reservacion reservacion) {
        reservacionDao.deleteReservacion(reservacion);
    }
    
}
