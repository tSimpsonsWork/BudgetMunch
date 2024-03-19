package com.example.project2.service;

import com.example.project2.entity.Student;
import com.example.project2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
       if(studentOptional.isPresent()){
           throw new IllegalStateException("Email taken");
       }
       studentRepository.save(student);
    }

    public void deletingStudent(Long studentId) {
           boolean exists = studentRepository.existsById(studentId);
            if(!exists){
                throw new IllegalStateException("student with id " + studentId + " does not exist");
            }
            studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
    Student student = studentRepository.findById(studentId).orElseThrow(()->
        new IllegalStateException("student with id " + studentId + " does not exist"));
    if(name != null &&
            name.length() > 0 &&
            !Objects.equals(student.getName(),name)){
        student.setName(name);
        }
    if(name != null &&
            name.length() > 0 &&
            !Objects.equals(student.getEmail(),email)){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        student.setEmail(email);
        }

    }




    //new Student(1L,"Miriam","miriam.hotmail@gmail.com",LocalDate.of(2000, Month.JANUARY,5),21)
}
