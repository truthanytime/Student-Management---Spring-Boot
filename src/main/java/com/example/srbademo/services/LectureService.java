package com.example.srbademo.services;

import com.example.srbademo.entities.Lecture;
import com.example.srbademo.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    public Lecture getLectureById(Long id) {
        return lectureRepository.findById(id).orElse(null);
    }

    public Lecture addLecture(Lecture lecture) {
        // Check if a lecture with the same name and field of study already exists
        Lecture existingLecture = lectureRepository.findByNameAndFieldOfStudy(lecture.getName(), lecture.getFieldOfStudy());

        if (existingLecture == null) {
            return lectureRepository.save(lecture);
        } else {
            throw new RuntimeException("Lecture with the same name and field of study already exists.");
        }
    }

    public Lecture updateLecture(Long id, Lecture lecture) {
        Lecture existingLecture = lectureRepository.findById(id).orElse(null);
        if (existingLecture != null) {
            existingLecture.setName(lecture.getName());
            existingLecture.setFieldOfStudy(lecture.getFieldOfStudy());
            // Set other attributes as needed

            return lectureRepository.save(existingLecture);
        }
        return null;
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public boolean deleteLecture(Long id) {
        if (lectureRepository.existsById(id)) {
            lectureRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

