/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.web;

import com.infotech.domain.HabitacionReporte;
import com.infotech.domain.Habitacion;
import com.infotech.domain.TipoHabitacion;
import com.infotech.servicio.HabitacionService;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.persistence.Query;
import org.primefaces.PrimeFaces;

/**
 *
 * @author InfoTech
 */
@Named("habitacionReportBean")
@ViewScoped
@RequestScoped
public class HabitacionReportBean {

    @Inject
    private HabitacionService habitacionService;
    
    private Habitacion habitacionSeleccionada;
    private TipoHabitacion tipoHabitacionSeleccionada;
    
    @PersistenceContext(unitName="sgPU")
    EntityManager em;

    @PostConstruct
    public void inicializar() {
        this.habitacionSeleccionada = new Habitacion();
        this.habitacionSeleccionada.setNumHabitacion(" ");
        this.tipoHabitacionSeleccionada = new TipoHabitacion();
        this.tipoHabitacionSeleccionada.setNombreHabitacion(" ");
    }

    public Habitacion getHabitacionSeleccionada() {
        return habitacionSeleccionada;
    }

    public void setHabitacionSeleccionada(Habitacion habitacionSeleccionada) {
        this.habitacionSeleccionada = habitacionSeleccionada;
    }

    public TipoHabitacion getTipoHabitacionSeleccionada() {
        return tipoHabitacionSeleccionada;
    }

    public void setTipoHabitacionSeleccionada(TipoHabitacion tipoHabitacionSeleccionada) {
        this.tipoHabitacionSeleccionada = tipoHabitacionSeleccionada;
    }
    
    public void reiniciarHabitacionSeleccionada() {
        this.habitacionSeleccionada = new Habitacion();
        this.habitacionSeleccionada.setNumHabitacion(" ");
        this.tipoHabitacionSeleccionada = new TipoHabitacion();
        this.tipoHabitacionSeleccionada.setNombreHabitacion(" ");
    }

    public void printPDF() throws JRException, IOException {
        List<HabitacionReporte> datasource = new ArrayList<>();
        
        if (!habitacionSeleccionada.getNumHabitacion().equals(" ") && tipoHabitacionSeleccionada.getNombreHabitacion().equals(" ")) {
            for (Habitacion h : habitacionService.encontrarHabitacionPorNumHabitacion(habitacionSeleccionada.getNumHabitacion())){
                datasource.add(new HabitacionReporte(h,h.getPrecioHabitacion().getPrecio()));
            }
        } else if (habitacionSeleccionada.getNumHabitacion().equals(" ") && !tipoHabitacionSeleccionada.getNombreHabitacion().equals(" ")) {
            for (Habitacion h : habitacionService.encontrarHabitacionPorTipoHabitacion(tipoHabitacionSeleccionada.getNombreHabitacion())){
                datasource.add(new HabitacionReporte(h,h.getPrecioHabitacion().getPrecio()));
            }
        } else if (!habitacionSeleccionada.getNumHabitacion().equals(" ") && !tipoHabitacionSeleccionada.getNombreHabitacion().equals(" ")) {
            Query query1 = em.createQuery("from TipoHabitacion th where th.nombreHabitacion = :nombreHabitacion");
            query1.setParameter("nombreHabitacion", tipoHabitacionSeleccionada.getNombreHabitacion());
            tipoHabitacionSeleccionada = (TipoHabitacion) query1.getSingleResult();
            Query query2 = em.createQuery("from Habitacion h where h.numHabitacion = :numHabitacion and h.tipoHabitacion = :tipoHabitacion");
            query2.setParameter("numHabitacion", habitacionSeleccionada.getNumHabitacion());
            query2.setParameter("tipoHabitacion", tipoHabitacionSeleccionada);
            List<Habitacion> habitaciones = query2.getResultList();
            for (Habitacion h : habitaciones){
                datasource.add(new HabitacionReporte(h,h.getPrecioHabitacion().getPrecio()));
            }
        }
        
        String filename ="habitaciones.pdf";
        String jasperPath = "/resources/reportes/reporteHabitaciones.jasper";
        
        if (!datasource.isEmpty()) {
            this.PDF(null, jasperPath, datasource, filename);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No se encontró ningún registro para generar el reporte");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public void PDF(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
        try {
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
            File file = new File(relativeWebPath);
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "inline;filename=" + fileName + ";");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(print, stream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
