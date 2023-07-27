package com.example.srbademo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String address;
    private String email;
    private String phone;
    @Transient
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}

