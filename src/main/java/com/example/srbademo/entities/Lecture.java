package com.example.srbademo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fieldOfStudy;
}

