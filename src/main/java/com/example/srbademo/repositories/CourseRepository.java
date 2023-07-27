package com.example.srbademo.repositories;

import com.example.srbademo.entities.Course;
import com.example.srbademo.models.CourseWithStudentCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    // Check if a course with the same name already exists
    boolean existsByName(String name);

    @Query(value = "SELECT " +
            "c.id," +
            "c.name," +
            "c.hours," +
            "c.max_students as maxStudents," +
            "(select count(*) from students where course_id=c.id) studentCount FROM courses c", nativeQuery = true)
    List<CourseWithStudentCount> findAllCoursesWithStudents();
}
