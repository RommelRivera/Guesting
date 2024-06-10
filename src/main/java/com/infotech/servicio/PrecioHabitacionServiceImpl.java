/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.servicio;

import com.infotech.datos.PrecioHabitacionDao;
import com.infotech.domain.PrecioHabitacion;
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
public class PrecioHabitacionServiceImpl implements PrecioHabitacionService{

    @Inject
    private PrecioHabitacionDao preciohabitacionDao;
    
    @Resource
    private SessionContext contexto;
    
    @Override
    public List<PrecioHabitacion> listarPrecioHabitaciones() {
        return preciohabitacionDao.findAllPrecioHabitaciones();
    }

    @Override
    public PrecioHabitacion encontrarPrecioHabitacionPorId(PrecioHabitacion precioHabitacion) {
        return preciohabitacionDao.findPrecioHabitacionById(precioHabitacion);
    }

    @Override
    public List<PrecioHabitacion> encontrarPrecioHabitacionPorPrecio(int precio) {
        return preciohabitacionDao.findPrecioHabitacionByPrecio(precio);
    }

    @Override
    public void registrarPrecioHabitacion(PrecioHabitacion precioHabitacion) {
        preciohabitacionDao.insertPrecioHabitacion(precioHabitacion);
    }

    @Override
    public void modificarPrecioHabitacion(PrecioHabitacion precioHabitacion) {
        try {
            preciohabitacionDao.updatePrecioHabitacion(precioHabitacion);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void eliminarPrecioHabitacion(PrecioHabitacion precioHabitacion) {
        preciohabitacionDao.deletePrecioHabitacion(precioHabitacion);
    }
    
}
