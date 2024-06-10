/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.servicio;

import com.infotech.datos.HabitacionDao;
import com.infotech.domain.Habitacion;
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
public class HabitacionServiceImpl implements HabitacionService{

    @Inject
    private HabitacionDao habitacionDao;
    
    @Resource
    private SessionContext contexto;
    
    @Override
    public List<Habitacion> listarHabitaciones() {
        return habitacionDao.findAllHabitaciones();
    }

    @Override
    public Habitacion encontrarHabitacionPorId(Habitacion habitacion) {
        return habitacionDao.findHabitacionById(habitacion);
    }

    @Override
    public List<Habitacion> encontrarHabitacionPorNumHabitacion(String numHabitacion) {
        return habitacionDao.findHabitacionByNumHabitacion(numHabitacion);
    }
    
     @Override
    public List<Habitacion> encontrarHabitacionPorTipoHabitacion(String nombreHabitacion) {
        return habitacionDao.findHabitacionByTipoHabitacion(nombreHabitacion);
    }

    @Override
    public void registrarHabitacion(Habitacion habitacion) {
        habitacionDao.insertHabitacion(habitacion);
    }

    @Override
    public void modificarHabitacion(Habitacion habitacion) {
        try {
            habitacionDao.updateHabitacion(habitacion);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void eliminarHabitacion(Habitacion habitacion) {
        habitacionDao.deleteHabitacion(habitacion);
    }
    
}
