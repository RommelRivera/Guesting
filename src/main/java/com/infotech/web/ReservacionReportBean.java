/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infotech.web;

import com.infotech.domain.Cliente;
import com.infotech.domain.Habitacion;
import com.infotech.domain.Reservacion;
import com.infotech.domain.ReservacionReporte;
import com.infotech.servicio.ReservacionService;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named("reservacionReportBean")
@ViewScoped
@RequestScoped
public class ReservacionReportBean {

    @Inject
    private ReservacionService reservacionService;
    
    private Reservacion reservacionSeleccionada;
    private Cliente clienteSeleccionado;
    private Habitacion habitacionSeleccionada;
    
    private Date date;
    
    @PersistenceContext(unitName="sgPU")
    EntityManager em;

    @PostConstruct
    public void inicializar() {
        this.reservacionSeleccionada = new Reservacion();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date = format.parse("01/01/2000");
        } catch (ParseException ex) {
            Logger.getLogger(ReservacionReportBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.reservacionSeleccionada.setFechaEntrada(date);
        this.reservacionSeleccionada.setFechaSalida(date);
        System.out.println(reservacionSeleccionada.getFechaEntrada() + " " + reservacionSeleccionada.getFechaSalida());
        this.clienteSeleccionado = new Cliente();
        this.clienteSeleccionado.setDocIdentidad(" ");
        this.habitacionSeleccionada = new Habitacion();
        this.habitacionSeleccionada.setNumHabitacion(" ");
    }

    public Reservacion getReservacionSeleccionada() {
        return reservacionSeleccionada;
    }

    public void setReservacionSeleccionada(Reservacion reservacionSeleccionada) {
        this.reservacionSeleccionada = reservacionSeleccionada;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public Habitacion getHabitacionSeleccionada() {
        return habitacionSeleccionada;
    }

    public void setHabitacionSeleccionada(Habitacion habitacionSeleccionada) {
        this.habitacionSeleccionada = habitacionSeleccionada;
    }
    
    public void reiniciarReservacionSeleccionada() {
        this.reservacionSeleccionada = new Reservacion();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date = format.parse("01/01/2000");
        } catch (ParseException ex) {
            Logger.getLogger(ReservacionReportBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(date);
        this.reservacionSeleccionada.setFechaEntrada(date);
        this.reservacionSeleccionada.setFechaSalida(date);
        System.out.println(reservacionSeleccionada.getFechaEntrada() + " " + reservacionSeleccionada.getFechaSalida());
        this.clienteSeleccionado = new Cliente();
        this.clienteSeleccionado.setDocIdentidad(" ");
        this.habitacionSeleccionada = new Habitacion();
        this.habitacionSeleccionada.setNumHabitacion(" ");
    }

    public void printPDF() throws JRException, IOException {
        List<ReservacionReporte> datasource = new ArrayList<>();
        
        System.out.println(reservacionSeleccionada.getFechaEntrada());
        
        if (!reservacionSeleccionada.getFechaEntrada().equals(date) && reservacionSeleccionada.getFechaSalida().equals(date)
                && clienteSeleccionado.getDocIdentidad().equals(" ") && habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            for (Reservacion r : reservacionService.encontrarReservacionPorFechaEntrada(reservacionSeleccionada.getFechaEntrada())){
                datasource.add(new ReservacionReporte(r,r.getCliente(),r.getHabitacion()));
            }
        } else if (reservacionSeleccionada.getFechaEntrada().equals(date) && !reservacionSeleccionada.getFechaSalida().equals(date)
                && clienteSeleccionado.getDocIdentidad().equals(" ") && habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            for (Reservacion r : reservacionService.encontrarReservacionPorFechaSalida(reservacionSeleccionada.getFechaSalida())){
                datasource.add(new ReservacionReporte(r,r.getCliente(),r.getHabitacion()));
            }
        } else if (!reservacionSeleccionada.getFechaEntrada().equals(date) && !reservacionSeleccionada.getFechaSalida().equals(date)
                && clienteSeleccionado.getDocIdentidad().equals(" ") && habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            Query query = em.createQuery("from Reservacion r where r.fechaEntrada = :fechaEntrada and r.fechaSalida = :fechaSalida");
            query.setParameter("fechaEntrada", reservacionSeleccionada.getFechaEntrada());
            query.setParameter("fechaSalida", reservacionSeleccionada.getFechaSalida());
            List<Reservacion> reservaciones = query.getResultList();
            for (Reservacion r : reservaciones){
                datasource.add(new ReservacionReporte(r,r.getCliente(),r.getHabitacion()));
            }
        } else if (reservacionSeleccionada.getFechaEntrada().equals(date) && reservacionSeleccionada.getFechaSalida().equals(date)
                && !clienteSeleccionado.getDocIdentidad().equals(" ") && habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            Query query1 = em.createQuery("from Cliente c where c.docIdentidad = :docIdentidad");
            query1.setParameter("docIdentidad", clienteSeleccionado.getDocIdentidad());
            clienteSeleccionado = (Cliente) query1.getSingleResult();
            Query query2 = em.createQuery("from Reservacion r where r.cliente = :cliente");
            query2.setParameter("cliente", clienteSeleccionado);
            List<Reservacion> reservaciones = query2.getResultList();
            for (Reservacion r : reservaciones){
                datasource.add(new ReservacionReporte(r,r.getCliente(),r.getHabitacion()));
            }
        } else if (!reservacionSeleccionada.getFechaEntrada().equals(date) && reservacionSeleccionada.getFechaSalida().equals(date)
                && !clienteSeleccionado.getDocIdentidad().equals(" ") && habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            Query query1 = em.createQuery("from Cliente c where c.docIdentidad = :docIdentidad");
            query1.setParameter("docIdentidad", clienteSeleccionado.getDocIdentidad());
            clienteSeleccionado = (Cliente) query1.getSingleResult();
            Query query2 = em.createQuery("from Reservacion r where r.fechaEntrada = :fechaEntrada and r.cliente = :cliente");
            query2.setParameter("fechaEntrada", reservacionSeleccionada.getFechaEntrada());
            query2.setParameter("cliente", clienteSeleccionado);
            List<Reservacion> reservaciones = query2.getResultList();
            for (Reservacion r : reservaciones){
                datasource.add(new ReservacionReporte(r,r.getCliente(),r.getHabitacion()));
            }
        } else if (reservacionSeleccionada.getFechaEntrada().equals(date) && !reservacionSeleccionada.getFechaSalida().equals(date)
                && !clienteSeleccionado.getDocIdentidad().equals(" ") && habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            Query query1 = em.createQuery("from Cliente c where c.docIdentidad = :docIdentidad");
            query1.setParameter("docIdentidad", clienteSeleccionado.getDocIdentidad());
            clienteSeleccionado = (Cliente) query1.getSingleResult();
            Query query2 = em.createQuery("from Reservacion r where r.fechaSalida = :fechaSalida and r.cliente = :cliente");
            query2.setParameter("fechaSalida", reservacionSeleccionada.getFechaEntrada());
            query2.setParameter("cliente", clienteSeleccionado);
            List<Reservacion> reservaciones = query2.getResultList();
            for (Reservacion r : reservaciones){
                datasource.add(new ReservacionReporte(r,r.getCliente(),r.getHabitacion()));
            }
        } else if (!reservacionSeleccionada.getFechaEntrada().equals(date) && !reservacionSeleccionada.getFechaSalida().equals(date)
                && !clienteSeleccionado.getDocIdentidad().equals(" ") && habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            Query query1 = em.createQuery("from Cliente c where c.docIdentidad = :docIdentidad");
            query1.setParameter("docIdentidad", clienteSeleccionado.getDocIdentidad());
            clienteSeleccionado = (Cliente) query1.getSingleResult();
            Query query2 = em.createQuery("from Reservacion r where r.fechaEntrada = :fechaEntrada and r.fechaSalida = :fechaSalida and r.cliente = :cliente");
            query2.setParameter("fechaEntrada", reservacionSeleccionada.getFechaEntrada());
            query2.setParameter("fechaSalida", reservacionSeleccionada.getFechaSalida());
            query2.setParameter("cliente", clienteSeleccionado);
            List<Reservacion> reservaciones = query2.getResultList();
            for (Reservacion r : reservaciones){
                datasource.add(new ReservacionReporte(r,r.getCliente(),r.getHabitacion()));
            }
        } else if (reservacionSeleccionada.getFechaEntrada().equals(date) && reservacionSeleccionada.getFechaSalida().equals(date)
                && clienteSeleccionado.getDocIdentidad().equals(" ") && !habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            List<Reservacion> reservaciones = new ArrayList<>();
            for(Reservacion room : reservacionService.listarReservaciones()){
                if(room.getHabitacion().getNumHabitacion().equals(habitacionSeleccionada.getNumHabitacion())){
                    for(Reservacion cuarto : reservacionService.encontrarReservacionPorHabitacion(room.getHabitacion())){
                        if(!reservaciones.contains(cuarto)){
                            reservaciones.add(cuarto);
                        }
                    }
                }
            }
            for (Reservacion r : reservaciones){
                datasource.add(new ReservacionReporte(r,r.getCliente(),r.getHabitacion()));
            }
        } else if (!reservacionSeleccionada.getFechaEntrada().equals(date) && reservacionSeleccionada.getFechaSalida().equals(date)
                && clienteSeleccionado.getDocIdentidad().equals(" ") && !habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            for(Reservacion room : reservacionService.listarReservaciones()){
                if(room.getHabitacion().getNumHabitacion().equals(habitacionSeleccionada.getNumHabitacion())
                        && room.getFechaEntrada().equals(reservacionSeleccionada.getFechaEntrada())){
                    Query query = em.createQuery("from Reservacion r where r.habitacion = :habitacion and r.fechaEntrada = :fechaEntrada");
                    query.setParameter("habitacion", room.getHabitacion());
                    query.setParameter("fechaEntrada", reservacionSeleccionada.getFechaEntrada());
                    room = (Reservacion) query.getSingleResult();
                    datasource.add(new ReservacionReporte(room,room.getCliente(),room.getHabitacion()));
                }
            }
        } else if (reservacionSeleccionada.getFechaEntrada().equals(date) && !reservacionSeleccionada.getFechaSalida().equals(date)
                && clienteSeleccionado.getDocIdentidad().equals(" ") && !habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            for(Reservacion room : reservacionService.listarReservaciones()){
                if(room.getHabitacion().getNumHabitacion().equals(habitacionSeleccionada.getNumHabitacion())
                        && room.getFechaSalida().equals(reservacionSeleccionada.getFechaSalida())){
                    Query query = em.createQuery("from Reservacion r where r.habitacion = :habitacion and r.fechaSalida = :fechaSalida");
                    query.setParameter("habitacion", room.getHabitacion());
                    query.setParameter("fechaSalida", reservacionSeleccionada.getFechaSalida());
                    room = (Reservacion) query.getSingleResult();
                    datasource.add(new ReservacionReporte(room,room.getCliente(),room.getHabitacion()));
                }
            }
        } else if (!reservacionSeleccionada.getFechaEntrada().equals(date) && !reservacionSeleccionada.getFechaSalida().equals(date)
                && clienteSeleccionado.getDocIdentidad().equals(" ") && !habitacionSeleccionada.getNumHabitacion().equals(" ")) {
            for(Reservacion room : reservacionService.listarReservaciones()){
                if(room.getHabitacion().getNumHabitacion().equals(habitacionSeleccionada.getNumHabitacion())
                        && room.getFechaEntrada().equals(reservacionSeleccionada.getFechaEntrada())
                        && room.getFechaSalida().equals(reservacionSeleccionada.getFechaSalida())){
                    Query query = em.createQuery("from Reservacion r where r.habitacion = :habitacion and r.fechaEntrada = :fechaEntrada and r.fechaSalida = :fechaSalida");
                    query.setParameter("habitacion", room.getHabitacion());
                    query.setParameter("fechaEntrada", reservacionSeleccionada.getFechaEntrada());
                    query.setParameter("fechaSalida", reservacionSeleccionada.getFechaSalida());
                    room = (Reservacion) query.getSingleResult();
                    datasource.add(new ReservacionReporte(room,room.getCliente(),room.getHabitacion()));
                }
            }
        }
        
        String filename ="reservaciones.pdf";
        String jasperPath = "/resources/reportes/reporteReservaciones.jasper";
        
        if (!datasource.isEmpty()) {
            this.PDF(null, jasperPath, datasource, filename);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No se encontró ningún registro para generar el reporte");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }
    
    public void printFacturaPDF() throws JRException, IOException {
        List<ReservacionReporte> datasource = new ArrayList<>();
        
        if (!clienteSeleccionado.getDocIdentidad().equals(" ") && !reservacionSeleccionada.getFechaEntrada().equals(date)) {
            Query query1 = em.createQuery("from Cliente c where c.docIdentidad = :docIdentidad");
            query1.setParameter("docIdentidad", clienteSeleccionado.getDocIdentidad());
            clienteSeleccionado = (Cliente) query1.getSingleResult();
            Query query2 = em.createQuery("from Reservacion r where r.cliente = :cliente and r.fechaEntrada = :fechaEntrada");
            query2.setParameter("cliente", clienteSeleccionado);
            query2.setParameter("fechaEntrada", reservacionSeleccionada.getFechaEntrada());
            List<Reservacion> reservaciones = query2.getResultList();
            for(Reservacion r : reservaciones){
                datasource.add(new ReservacionReporte(r,r.getCliente(),r.getHabitacion()));
            }
        }
        
        String filename ="factura.pdf";
        String jasperPath = "/resources/reportes/facturaReservaciones.jasper";
        
        if (!datasource.isEmpty()) {
            this.PDF(null, jasperPath, datasource, filename);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No se encontró ningún registro para generar la factura");
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
