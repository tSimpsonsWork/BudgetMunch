package com.example.project2.service;

import com.example.project2.entity.Student;
import com.example.project2.entity.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class StudentService {
    //TODO: Add a goggle map search
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(List<Student> student) {
        studentRepository.saveAllAndFlush(student);
    }
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

}
