package com.example.srbademo.services;

import com.example.srbademo.entities.Course;
import com.example.srbademo.models.CourseWithStudentCount;
import com.example.srbademo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<CourseWithStudentCount> findAllCoursesWithStudents() {
        return courseRepository.findAllCoursesWithStudents();
    }

    public Course addCourse(Course course) {
        if (courseRepository.existsByName(course.getName())) {
            throw new RuntimeException("Course with the same name already exists.");
        }

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse != null) {
            // Update the existing course with the new details
            existingCourse.setName(course.getName());
            existingCourse.setMaxStudents(course.getMaxStudents());
            // Set other attributes as needed

            return courseRepository.save(existingCourse);
        }
        return null; // Return null if the course with the given id is not found
    }

    public boolean deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            courseRepository.delete(course);
            return true;
        }
        return false;
    }
}

