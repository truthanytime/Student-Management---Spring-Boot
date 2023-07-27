package com.example.srbademo.controllers;

import com.example.srbademo.models.CourseWithStudentCount;
import com.example.srbademo.services.CourseService;
import com.example.srbademo.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private CourseService courseService;


    @GetMapping("/getAllCoursesWithStudentCount")
    public ResponseEntity<ApiResponse<List<CourseWithStudentCount>>> getAllCoursesWithStudentCount() {
        List<CourseWithStudentCount> courses = courseService.findAllCoursesWithStudents();
        ApiResponse<List<CourseWithStudentCount>> response;

        if (!courses.isEmpty())
            response = new ApiResponse<>(HttpStatus.OK.value(), true, "Courses with count of students", courses);
        else
            response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "No courses found");

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
