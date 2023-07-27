package com.example.srbademo.services;

import com.example.srbademo.entities.Course;
import com.example.srbademo.entities.Student;
import com.example.srbademo.repositories.CourseRepository;
import com.example.srbademo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        // Check for duplicate email
        if (isEmailAlreadyExists(student.getEmail())) {
            throw new RuntimeException("Email already exists: " + student.getEmail());
        }

        if( student.getCourseId() != null ){
            Optional<Course> candidate = courseRepository.findById(student.getCourseId());
            Course course = candidate.orElse( null );
            student.setCourse( course );
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            // Update the existing student with the new details
            existingStudent.setName(student.getName());
            existingStudent.setAge(student.getAge());
            existingStudent.setAddress(student.getAddress());
            existingStudent.setCourseId(student.getCourseId());
            existingStudent.setPhone(student.getPhone());
            if( student.getCourseId() != null ){
                Optional<Course> candidate = courseRepository.findById(student.getCourseId());
                Course course = candidate.orElse( null );
                existingStudent.setCourse( course );
            }

            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private boolean isEmailAlreadyExists(String email) {
        Student existingStudent = studentRepository.findByEmail(email);
        return existingStudent != null;
    }
}

