package com.example.srbademo.controllers;

import com.example.srbademo.entities.Lecture;
import com.example.srbademo.services.LectureService;
import com.example.srbademo.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Lecture>> getLectureById(@PathVariable Long id) {
        ApiResponse<Lecture> response;
        try {
            Lecture lecture = lectureService.getLectureById(id);

            if (lecture != null)
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "Lecture data", lecture);
            else
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "Lecture not found.");
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Lecture>>> getAllLectures() {
        ApiResponse<List<Lecture>> response;
        try {
            List<Lecture> lectures = lectureService.getAllLectures();

            if (!lectures.isEmpty()) {
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "List of lectures", lectures);
            } else {
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "No lectures found.");
            }
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Lecture>> addLecture(@RequestBody Lecture lecture) {
        ApiResponse<Lecture> response;
        try {
            Lecture newLecture = lectureService.addLecture(lecture);

            if (newLecture != null) {
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "Added new lecture successfully", newLecture);
            } else {
                response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Failed to add new lecture.");
            }
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Lecture>> updateLecture(@PathVariable Long id, @RequestBody Lecture lecture) {
        ApiResponse<Lecture> response;
        try {
            Lecture updatedLecture = lectureService.updateLecture(id, lecture);

            if (updatedLecture != null) {
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "Updated lecture successfully", updatedLecture);
            } else {
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "Lecture not found.");
            }
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteLecture(@PathVariable Long id) {
        ApiResponse<String> response;
        try {
            boolean deleted = lectureService.deleteLecture(id);

            if (deleted) {
                response = new ApiResponse<>(HttpStatus.OK.value(), true, "Lecture with ID " + id + " has been deleted successfully.", "Lecture with ID " + id + " has been deleted successfully.");
            } else {
                response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), false, "Lecture not found.");
            }
        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, e.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

