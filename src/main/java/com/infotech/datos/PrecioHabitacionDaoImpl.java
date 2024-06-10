/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.PrecioHabitacion;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author InfoTech
 */
public class PrecioHabitacionDaoImpl implements PrecioHabitacionDao{
    
    @PersistenceContext(unitName="sgPU")
    EntityManager em;

    @Override
    public List<PrecioHabitacion> findAllPrecioHabitaciones() {
        return em.createNamedQuery("PrecioHabitacion.findAll").getResultList();
    }

    @Override
    public PrecioHabitacion findPrecioHabitacionById(PrecioHabitacion precioHabitacion) {
        return em.find(PrecioHabitacion.class, precioHabitacion.getIdPrecio());
    }

    @Override
    public List<PrecioHabitacion> findPrecioHabitacionByPrecio(int precio) {
        Query query = em.createQuery("from PrecioHabitacion ph where ph.precio =: precio");
        query.setParameter("precio", precio);
        
        return query.getResultList();
    }

    @Override
    public void insertPrecioHabitacion(PrecioHabitacion precioHabitacion) {
        em.persist(precioHabitacion);
    }

    @Override
    public void updatePrecioHabitacion(PrecioHabitacion precioHabitacion) {
        em.merge(precioHabitacion);
    }

    @Override
    public void deletePrecioHabitacion(PrecioHabitacion precioHabitacion) {
        em.remove(em.merge(precioHabitacion));
    }
    
}
