package com.example.project2.entity.repository;

import com.example.project2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Override
    void deleteAll();
}
