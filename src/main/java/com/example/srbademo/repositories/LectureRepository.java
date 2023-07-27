package com.example.srbademo.repositories;

import com.example.srbademo.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture findByNameAndFieldOfStudy(String name, String fieldOfStudy);
}