/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isabella.filmFusion.controller.report;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isabella.filmFusion.model.Cinema;
import com.isabella.filmFusion.service.CinemaService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api/v1/report/cinema")
public class CinemaReportController {
    @Autowired
    private CinemaService cinemaService;
    
    @GetMapping(
        value = "/todos",
        produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<InputStreamResource> getCinemaReport() throws IOException {
        Date dIni = new Date();
        
        File file = ResourceUtils.getFile("classpath:report/CineFusion.jasper");
        InputStream inputStream = new FileInputStream(file);
        //OutputStream outputStream = new ByteArrayOutputStream();

        List<Cinema> cinemaList = cinemaService.consultar();

        Map parameters = new HashMap();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(cinemaList);
        byte [] byteReporte = null;
        try {
            JasperPrint print = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
            byteReporte = JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            e.printStackTrace();
        }
        
        Date dFim = new Date();
        System.out.println("Fim report: " + (dFim.getTime() - dIni.getTime()) );

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(byteReporte)));

    }
}
