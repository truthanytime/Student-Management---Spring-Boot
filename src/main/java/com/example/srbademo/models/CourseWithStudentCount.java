package com.example.srbademo.models;

public interface CourseWithStudentCount {
    Long getId();
    String getName();
    int getHours();
    int getMaxStudents();
    int getStudentCount();
}
