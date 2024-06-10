/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.datos;

import com.infotech.domain.Cliente;
import java.util.List;

/**
 *
 * @author InfoTech
 */
public interface ClienteDao {
    public List<Cliente> findAllClientes();
    
    public Cliente findClienteById(Cliente cliente);
    
    public List<Cliente> findClienteByNombres(String nombres);
    
    public List<Cliente> findClienteByApellidos(String apellidos);
    
    public List<Cliente> findClienteByDocIdentidad(String docIdentidad);
    
    public List<Cliente> findClienteByCorreo(String correo);
    
    public void insertCliente(Cliente cliente);
    
    public void updateCliente(Cliente cliente);
    
    public void deleteCliente(Cliente cliente);
}
