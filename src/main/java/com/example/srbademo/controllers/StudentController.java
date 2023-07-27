package com.example.srbademo.controllers;

import com.example.srbademo.entities.Student;
import com.example.srbademo.services.StudentService;
import com.example.srbademo.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Long id) {
        ApiResponse<Student> response;
        try {
            Student student = studentService.getStudentById(id);

            if (student != null)
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "Student data", student);
            else
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "not found student");
        }
        catch (Exception e){
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody Student student) {
        ApiResponse<Student> response;
        try {
            Student newStudent = studentService.addStudent(student);

            if (newStudent != null)
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "Student added successfully", newStudent);
            else
                response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Failed to add student");
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        ApiResponse<Student> response;
        try {
            Student updatedStudent = studentService.updateStudent(id, student);

            if (updatedStudent != null)
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "Student updated successfully", updatedStudent);
            else
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "Student not found");
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        ApiResponse<List<Student>> response;
        try {
            List<Student> students = studentService.getAllStudents();

            if (!students.isEmpty())
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "List of students", students);
            else
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "No students found");
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable Long id) {
        ApiResponse<String> response;
        try {
            boolean deleted = studentService.deleteStudent(id);

            if (deleted)
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "Student with ID " + id + " has been deleted successfully");
            else
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "Student not found");
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}


