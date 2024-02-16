package com.example.demo.controllers;

import com.example.demo.entitites.PDF;
import com.example.demo.services.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pdf")
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @GetMapping("/student/{studentId}")
    public List<PDF> getPDFByStudentId(@PathVariable Long studentId){
        return pdfService.findByStudentId(studentId);
    }
}
