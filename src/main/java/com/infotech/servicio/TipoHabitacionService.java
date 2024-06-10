/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.servicio;


import com.infotech.domain.TipoHabitacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author InfoTech
 */
@Local
public interface TipoHabitacionService {
    public List<TipoHabitacion> listarTipoHabitaciones();
    
    public List<String> listarNombreHabitaciones();
    
    public TipoHabitacion encontrarTipoHabitacionPorId(TipoHabitacion tipoHabitacion);
    
    public List<TipoHabitacion> encontrarTipoHabitacionPorNombreHabitacion(String numHabitacion);
    
    public void registrarTipoHabitacion(TipoHabitacion tipoHabitacion);
    
    public void modificarTipoHabitacion (TipoHabitacion tipoHabitacion);
    
    public void eliminarTipoHabitacion (TipoHabitacion tipoHabitacion);
}

