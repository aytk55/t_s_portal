package com.example.demo.controllers;

import com.example.demo.entitites.Student;
import com.example.demo.entitites.Teacher;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AuthService(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public boolean loginAsTeacher(LoginRequest loginRequest) {
        Optional<Teacher> optionalTeacher = teacherRepository.findByUsername(loginRequest.getUsername());

        return optionalTeacher.isPresent() &&
                optionalTeacher.get().getPassword().equals(loginRequest.getPassword());
    }

    public boolean loginAsStudent(LoginRequest loginRequest) {
        Optional<Student> optionalStudent = studentRepository.findByUsername(loginRequest.getUsername());

        return optionalStudent.isPresent() &&
                optionalStudent.get().getPassword().equals(loginRequest.getPassword());
    }



}
