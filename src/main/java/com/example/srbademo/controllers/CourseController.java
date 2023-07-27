package com.example.srbademo.controllers;

import com.example.srbademo.entities.Course;
import com.example.srbademo.services.CourseService;
import com.example.srbademo.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        ApiResponse<Course> response;

        if (course != null)
            response = new ApiResponse<>(HttpStatus.OK.value(), true, "Course data", course);
        else
            response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "Course not found");

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        ApiResponse<List<Course>> response;

        if (!courses.isEmpty())
            response = new ApiResponse<>(HttpStatus.OK.value(), true, "Courses data", courses);
        else
            response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "No courses found");

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> addCourse(@RequestBody Course course) {
        ApiResponse<Course> response;
        try {
            Course newCourse = courseService.addCourse(course);
            if (newCourse != null)
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "Added new course successfully", newCourse);
            else
                response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Failed to add new course");
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        ApiResponse<Course> response;

        if (updatedCourse != null)
            response = new ApiResponse<>(HttpStatus.OK.value(), true, "Updated course data", updatedCourse);
        else
            response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "Course not found");

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        ApiResponse<String> response;

        if (deleted)
            response = new ApiResponse<>(HttpStatus.OK.value(), true, "Course with ID " + id + " has been deleted successfully");
        else
            response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "Course not found");

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}



