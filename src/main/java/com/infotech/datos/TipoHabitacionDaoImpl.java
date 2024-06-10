/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.TipoHabitacion;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author InfoTech
 */
public class TipoHabitacionDaoImpl implements TipoHabitacionDao{
    
    @PersistenceContext(unitName="sgPU")
    EntityManager em;

    @Override
    public List<TipoHabitacion> findAllTipoHabitaciones() {
        return em.createNamedQuery("TipoHabitacion.findAll").getResultList();
    }
    
    @Override
    public List<String> findAllNombreHabitaciones() {
        Query query = em.createQuery("select th.nombreHabitacion from TipoHabitacion th");
        
        return query.getResultList();
    }

    @Override
    public TipoHabitacion findTipoHabitacionById(TipoHabitacion tipoHabitacion) {
        return em.find(TipoHabitacion.class, tipoHabitacion.getIdTipo());
    }

    @Override
    public List<TipoHabitacion> findTipoHabitacionByNombreHabitacion(String nombreHabitacion) {
        Query query = em.createQuery("from TipoHabitacion th where th.nombreHabitacion = :nombreHabitacion");
        query.setParameter("nombreHabitacion", nombreHabitacion);
        
        return query.getResultList();
    }

    @Override
    public void insertTipoHabitacion(TipoHabitacion tipoHabitacion) {
        
    }

    @Override
    public void updateTipoHabitacion(TipoHabitacion tipoHabitacion) {
        em.merge(tipoHabitacion);
    }

    @Override
    public void deleteTipoHabitacion(TipoHabitacion tipoHabitacion) {
        em.remove(em.merge(tipoHabitacion));
    }   
    
}
