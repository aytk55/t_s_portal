package com.example.demo.services;

import com.example.demo.entitites.PDF;
import com.example.demo.repositories.PDFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDFService {
    @Autowired
    private PDFRepository pdfRepository;
    public List<PDF> findByStudentId(Long studentId) {
        return pdfRepository.findByStudentId(studentId);
    }
}
