package com.example.demo.repositories;

import com.example.demo.entitites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
