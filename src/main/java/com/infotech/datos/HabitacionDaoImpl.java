/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.Habitacion;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author InfoTech
 */
public class HabitacionDaoImpl implements HabitacionDao{
    
    @PersistenceContext(unitName="sgPU")
    EntityManager em;

    @Override
    public List<Habitacion> findAllHabitaciones() {
        return em.createNamedQuery("Habitacion.findAll").getResultList();
    }

    @Override
    public Habitacion findHabitacionById(Habitacion habitacion) {
        return em.find(Habitacion.class, habitacion.getIdHabitacion());
    }

    @Override
    public List<Habitacion> findHabitacionByNumHabitacion(String numHabitacion) {
        Query query = em.createQuery("from Habitacion h where h.numHabitacion = :numHabitacion");
        query.setParameter("numHabitacion", numHabitacion);
        
        return query.getResultList();
    }

    @Override
    public List<Habitacion> findHabitacionByTipoHabitacion(String nombreHabitacion) {
        Query query = em.createQuery("from Habitacion h where h.tipoHabitacion = (select th from TipoHabitacion th where th.nombreHabitacion = :nombreHabitacion)");
        query.setParameter("nombreHabitacion", nombreHabitacion);
        
        return query.getResultList();
    }

    @Override
    public void insertHabitacion(Habitacion habitacion) {
        
    }

    @Override
    public void updateHabitacion(Habitacion habitacion) {
        em.merge(habitacion);
    }

    @Override
    public void deleteHabitacion(Habitacion habitacion) {
        em.remove(em.merge(habitacion));
    }
    
}
