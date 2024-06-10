/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.servicio;

import com.infotech.datos.ClienteDao;
import com.infotech.domain.Cliente;
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
public class ClienteServiceImpl implements ClienteService{
    
    @Inject
    private ClienteDao clienteDao;
    
    @Resource
    private SessionContext contexto;

    @Override
    public List<Cliente> listarClientes() {
        return clienteDao.findAllClientes();
    }

    @Override
    public Cliente encontrarClientePorId(Cliente cliente) {
        return clienteDao.findClienteById(cliente);
    }

    @Override
    public List<Cliente> encontrarClientePorNombres(String nombres) {
        return clienteDao.findClienteByNombres(nombres);
    }
    
     @Override
    public List<Cliente> encontrarClientePorApellidos(String apellidos) {
        return clienteDao.findClienteByApellidos(apellidos);
    }
    
     @Override
    public List<Cliente> encontrarClientePorDocIdentidad(String docIdentidad) {
        return clienteDao.findClienteByDocIdentidad(docIdentidad);
    }
    
    @Override
    public List<Cliente> encontrarClientePorCorreo(String correo) {
        return clienteDao.findClienteByCorreo(correo);
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        clienteDao.insertCliente(cliente);
    }
    
    @Override
    public void modificarCliente(Cliente cliente) {
        try {
            clienteDao.updateCliente(cliente);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteDao.deleteCliente(cliente);
    }
}
