/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infotech.servicio;


import com.infotech.domain.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author InfoTech
 */
@Local
public interface ClienteService {
    public List<Cliente> listarClientes();
    
    public Cliente encontrarClientePorId(Cliente cliente);
    
    public List<Cliente> encontrarClientePorNombres(String nombres);
    
    public List<Cliente> encontrarClientePorApellidos(String apellidos);
    
    public List<Cliente> encontrarClientePorDocIdentidad(String docIdentidad);
    
    public List<Cliente> encontrarClientePorCorreo(String correo); 
    
    public void registrarCliente(Cliente cliente);
    
    public void modificarCliente (Cliente cliente);
    
    public void eliminarCliente (Cliente cliente);
}

