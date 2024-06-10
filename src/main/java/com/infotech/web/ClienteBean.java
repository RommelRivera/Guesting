/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.web;

import com.infotech.domain.Cliente;
import com.infotech.servicio.ClienteService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author InfoTech
 */

@Named ("clienteBean")
@RequestScoped
public class ClienteBean {
    @Inject
    private ClienteService clienteService;
    
    private Cliente clienteSeleccionado;
    
    List<Cliente> clientes;
    
    List<Cliente> clientesFiltrados;
    
    public ClienteBean() {
    }
    
    @PostConstruct
    
    public void inicializar() {
        this.clientes = clienteService.listarClientes();
        this.clienteSeleccionado = new Cliente();
    }
    
    public void editListener(RowEditEvent event) {
        Cliente cliente = (Cliente) event.getObject();
        clienteService.modificarCliente(cliente);
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }
    
    public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
        this.clientesFiltrados = clientesFiltrados;
    }
    
    public void agregarCliente() {
        this.clienteService.registrarCliente(clienteSeleccionado);
        this.clientes.add(clienteSeleccionado);
        this.clienteSeleccionado = null;
    }

    public void eliminarCliente() {
        this.clienteService.eliminarCliente(clienteSeleccionado);
        this.clientes.remove(clienteSeleccionado);
        this.clienteSeleccionado = null;
    }
    
    public void reiniciarClienteSeleccionado() {
        this.clienteSeleccionado = new Cliente();
    }
}
