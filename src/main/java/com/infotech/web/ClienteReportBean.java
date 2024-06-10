/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.web;

import com.infotech.domain.Cliente;
import com.infotech.servicio.ClienteService;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import javax.faces.bean.ViewScoped;
import javax.inject.*;
import javax.persistence.*;
import org.primefaces.PrimeFaces;

/**
 *
 * @author InfoTech
 */

@Named("clienteReportBean")
@ViewScoped
@RequestScoped
public class ClienteReportBean {
    @Inject
    private ClienteService clienteService;
    
    private Cliente clienteSeleccionado;
    
    @PersistenceContext(unitName="sgPU")
    EntityManager em;
    
    @PostConstruct
    public void inicializar(){
        this.clienteSeleccionado = new Cliente();
        this.clienteSeleccionado.setNombres(" ");
        this.clienteSeleccionado.setApellidos(" ");
        this.clienteSeleccionado.setDocIdentidad(" ");
        this.clienteSeleccionado.setCorreo(" ");
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }
    
    public void reiniciarClienteSeleccionado(){
        this.clienteSeleccionado = new Cliente();
        this.clienteSeleccionado.setNombres(" ");
        this.clienteSeleccionado.setApellidos(" ");
        this.clienteSeleccionado.setDocIdentidad(" ");
        this.clienteSeleccionado.setCorreo(" ");
    }
    
    public void printPDF() throws JRException, IOException{
        List<Cliente> datasource = new ArrayList<>();
        
        if (!clienteSeleccionado.getNombres().equals(" ") && clienteSeleccionado.getApellidos().equals(" ")
                && clienteSeleccionado.getDocIdentidad().equals(" ") && clienteSeleccionado.getCorreo().equals(" ")) {
            datasource = clienteService.encontrarClientePorNombres(clienteSeleccionado.getNombres());
        } else if (clienteSeleccionado.getNombres().equals(" ") && !clienteSeleccionado.getApellidos().equals(" ")
                && clienteSeleccionado.getDocIdentidad().equals(" ") && clienteSeleccionado.getCorreo().equals(" ")) {
            datasource = clienteService.encontrarClientePorApellidos(clienteSeleccionado.getApellidos());
        } else if (!clienteSeleccionado.getNombres().equals(" ") && !clienteSeleccionado.getApellidos().equals(" ")
                && clienteSeleccionado.getDocIdentidad().equals(" ") && clienteSeleccionado.getCorreo().equals(" ")) {
            Query query = em.createQuery("from Cliente c where c.nombres = :nombres and c.apellidos = :apellidos");
            query.setParameter("nombres", clienteSeleccionado.getNombres());
            query.setParameter("apellidos", clienteSeleccionado.getApellidos());
            datasource = query.getResultList();
        } else if (!clienteSeleccionado.getDocIdentidad().equals(" ")) {
            datasource = clienteService.encontrarClientePorDocIdentidad(clienteSeleccionado.getDocIdentidad());
        } else if (!clienteSeleccionado.getCorreo().equals(" ")) {
            datasource = clienteService.encontrarClientePorCorreo(clienteSeleccionado.getCorreo());
        }
        
        String filename ="clientes.pdf";
        String jasperPath = "/resources/reportes/reporteClientes.jasper";
        
        if (!datasource.isEmpty()) {
            this.PDF(null, jasperPath, datasource, filename);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No se encontró ningún registro para generar el reporte");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public void PDF(Map<String,Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException{
        try {
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
            File file = new File(relativeWebPath);
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), params,source);
            HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "inline;filename="+fileName);
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(print, stream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
