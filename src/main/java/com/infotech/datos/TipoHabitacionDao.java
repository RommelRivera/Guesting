/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.TipoHabitacion;
import java.util.List;

/**
 *
 * @author InfoTech
 */
public interface TipoHabitacionDao {
    public List<TipoHabitacion> findAllTipoHabitaciones();
    
    public List<String> findAllNombreHabitaciones();
    
    public TipoHabitacion findTipoHabitacionById(TipoHabitacion tipoHabitacion);
    
    public List<TipoHabitacion> findTipoHabitacionByNombreHabitacion(String nombreHabitacion);
    
    public void insertTipoHabitacion(TipoHabitacion tipoHabitacion);
    
    public void updateTipoHabitacion(TipoHabitacion tipoHabitacion);
    
    public void deleteTipoHabitacion(TipoHabitacion tipoHabitacion);
}
