/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.Cliente;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author InfoTech
 */
public class ClienteDaoImpl implements ClienteDao{
    
    @PersistenceContext(unitName="sgPU")
    EntityManager em;

    @Override
    public List<Cliente> findAllClientes() {
        return em.createNamedQuery("Cliente.findAll").getResultList();
    }

    @Override
    public Cliente findClienteById(Cliente cliente) {
        return em.find(Cliente.class, cliente.getIdCliente());
    }
    
    @Override
    public List<Cliente> findClienteByNombres(String nombres) {
        Query query = em.createQuery("from Cliente c where c.nombres = :nombres");
        query.setParameter("nombres", nombres);
        
        return query.getResultList();
    }

    @Override
    public List<Cliente> findClienteByApellidos(String apellidos) {
        Query query = em.createQuery("from Cliente c where c.apellidos = :apellidos");
        query.setParameter("apellidos", apellidos);
        
        return query.getResultList();
    }

    @Override
    public List<Cliente> findClienteByDocIdentidad(String docIdentidad) {
        Query query = em.createQuery("from Cliente c where c.docIdentidad = :docIdentidad");
        query.setParameter("docIdentidad", docIdentidad);
        
        return query.getResultList();
    }

    @Override
    public List<Cliente> findClienteByCorreo(String correo) {
        Query query = em.createQuery("from Cliente c where c.correo = :correo");
        query.setParameter("correo", correo);
        
        return query.getResultList();
    }

    @Override
    public void insertCliente(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public void deleteCliente(Cliente cliente) {
        em.remove(em.merge(cliente));
    }
    
}
