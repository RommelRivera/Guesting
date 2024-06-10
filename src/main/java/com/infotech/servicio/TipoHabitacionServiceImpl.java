/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.servicio;

import com.infotech.datos.TipoHabitacionDao;
import com.infotech.domain.TipoHabitacion;
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
public class TipoHabitacionServiceImpl implements TipoHabitacionService{

    @Inject
    private TipoHabitacionDao tipoHabitacionDao;
    
    @Resource
    private SessionContext contexto;
    
    @Override
    public List<TipoHabitacion> listarTipoHabitaciones() {
        return tipoHabitacionDao.findAllTipoHabitaciones();
    }
    
    @Override
    public List<String> listarNombreHabitaciones() {
        return tipoHabitacionDao.findAllNombreHabitaciones();
    }

    @Override
    public TipoHabitacion encontrarTipoHabitacionPorId(TipoHabitacion tipoHabitacion) {
        return tipoHabitacionDao.findTipoHabitacionById(tipoHabitacion);
    }

    @Override
    public List<TipoHabitacion> encontrarTipoHabitacionPorNombreHabitacion(String numHabitacion) {
        return tipoHabitacionDao.findTipoHabitacionByNombreHabitacion(numHabitacion);
    }
    
    @Override
    public void registrarTipoHabitacion(TipoHabitacion tipoHabitacion) {
        tipoHabitacionDao.insertTipoHabitacion(tipoHabitacion);
    }

    @Override
    public void modificarTipoHabitacion(TipoHabitacion tipoHabitacion) {
        try {
            tipoHabitacionDao.updateTipoHabitacion(tipoHabitacion);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void eliminarTipoHabitacion(TipoHabitacion tipoHabitacion) {
        tipoHabitacionDao.deleteTipoHabitacion(tipoHabitacion);
    }
    
}
