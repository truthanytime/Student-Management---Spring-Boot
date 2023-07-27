package com.example.srbademo.repositories;

import com.example.srbademo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Custom method to find a student by email
    Student findByEmail(String email);
}
