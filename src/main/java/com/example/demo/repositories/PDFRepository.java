package com.example.demo.repositories;

import com.example.demo.entitites.PDF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PDFRepository extends JpaRepository<PDF,Integer> {
    List<PDF> findByUserId(Integer userId);
}
