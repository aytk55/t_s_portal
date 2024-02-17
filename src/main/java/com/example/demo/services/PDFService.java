package com.example.demo.services;

import com.example.demo.entitites.PDF;
import com.example.demo.jwt.user.User;
import com.example.demo.jwt.user.UserRepository;
import com.example.demo.repositories.PDFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDFService {
    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PDF> findByStudentId(Integer userId) {
        return pdfRepository.findByUserId(userId);
    }

    public void addPDFtoUser(String filename, String content, Integer userId) {
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));



        PDF pdf = new PDF();
        pdf.setFilename(filename);
        pdf.setContent(content);
        pdf.setUser(user);


        pdfRepository.save(pdf);
    }
}