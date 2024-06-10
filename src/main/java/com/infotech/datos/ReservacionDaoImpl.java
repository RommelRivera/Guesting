/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.Habitacion;
import com.infotech.domain.Reservacion;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author InfoTech
 */
public class ReservacionDaoImpl implements ReservacionDao{
    
    @PersistenceContext(unitName="sgPU")
    EntityManager em;

    @Override
    public List<Reservacion> findAllReservaciones() {
        return em.createNamedQuery("Reservacion.findAll").getResultList();
    }

    @Override
    public Reservacion findReservacionById(Reservacion reservacion) {
        return em.find(Reservacion.class, reservacion.getIdReservacion());
    }

    @Override
    public List<Reservacion> findReservacionByFechaEntrada(Date fechaEntrada) {
        Query query = em.createQuery("from Reservacion r where r.fechaEntrada = :fechaEntrada");
        query.setParameter("fechaEntrada", fechaEntrada);
        
        return query.getResultList();
    }

    @Override
    public List<Reservacion> findReservacionByFechaSalida(Date fechaSalida) {
        Query query = em.createQuery("from Reservacion r where r.fechaSalida = :fechaSalida");
        query.setParameter("fechaSalida", fechaSalida);
        
        return query.getResultList();
    }

    @Override
    public List<Reservacion> findReservacionByCliente(String docIdentidad) {
        Query query = em.createQuery("from Reservacion r where r.cliente = (select c from Cliente c where c.docIdentidad = :docIdentidad)");
        query.setParameter("docIdentidad", docIdentidad);
        
        return query.getResultList();
    }

    @Override
    public List<Reservacion> findReservacionByHabitacion(Habitacion habitacion) {
        Query query = em.createQuery("from Reservacion r where r.habitacion = :habitacion");
        query.setParameter("habitacion", habitacion);
        
        return query.getResultList();
    }

    @Override
    public void insertReservacion(Reservacion reservacion) {
        em.merge(reservacion);
        //em.persist(reservacion);
    }

    @Override
    public void updateReservacion(Reservacion reservacion) {
        em.merge(reservacion);
    }

    @Override
    public void deleteReservacion(Reservacion reservacion) {
        em.createQuery("delete from Reservacion r where r.idReservacion = :idReservacion").setParameter("idReservacion", reservacion.getIdReservacion()).executeUpdate();
        //em.remove(em.merge(reservacion));
    }
    
}
