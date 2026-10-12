package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.model.Course;
import com.workintech.spring17challenge.model.LowCourseGpa;
import com.workintech.spring17challenge.model.MediumCourseGpa;
import com.workintech.spring17challenge.model.HighCourseGpa;
import com.workintech.spring17challenge.validation.CourseValidator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final List<Course> courses = new ArrayList<>();
    private int idCounter = 1;

    private final LowCourseGpa lowCourseGpa;
    private final MediumCourseGpa mediumCourseGpa;
    private final HighCourseGpa highCourseGpa;
    private final CourseValidator courseValidator;

    public CourseController(
            LowCourseGpa lowCourseGpa,
            MediumCourseGpa mediumCourseGpa,
            HighCourseGpa highCourseGpa,
            CourseValidator courseValidator) {

        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
        this.courseValidator = courseValidator;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courses;
    }

    @GetMapping("/{name}")
    public Course getCourseByName(@PathVariable String name) {
        for (Course course : courses) {
            if (course.getName().equals(name)) {
                return course;
            }
        }

        throw new ApiException(
                "Course bulunamadı",
                HttpStatus.NOT_FOUND
        );
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addCourse(
            @RequestBody Course course) {

        courseValidator.validate(course);

        course.setId(idCounter);
        idCounter++;

        courses.add(course);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createResponse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCourse(
            @PathVariable int id,
            @RequestBody Course newCourse) {

        courseValidator.validate(newCourse);

        int index = findCourseIndexById(id);

        checkDuplicateName(newCourse.getName(), id);

        newCourse.setId(id);
        courses.set(index, newCourse);

        return ResponseEntity.ok(createResponse(newCourse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        int index = findCourseIndexById(id);
        courses.remove(index);

        return ResponseEntity.ok().build();
    }

    private int calculateTotalGpa(Course course) {
        int credit = course.getCredit();
        int coefficient = course.getGrade().getCoefficient();

        if (credit <= 2) {
            return coefficient * credit * lowCourseGpa.getGpa();
        } else if (credit == 3) {
            return coefficient * credit * mediumCourseGpa.getGpa();
        } else {
            return coefficient * credit * highCourseGpa.getGpa();
        }
    }

    private Map<String, Object> createResponse(Course course) {
        Map<String, Object> response = new HashMap<>();

        response.put("course", course);
        response.put("totalGpa", calculateTotalGpa(course));

        return response;
    }

    private void checkDuplicateName(String name, Integer excludedId) {
        for (Course course : courses) {
            if (course.getName().equals(name)
                    && (excludedId == null
                    || course.getId() != excludedId)) {

                throw new ApiException(
                        "Aynı isimde bir course zaten mevcut",
                        HttpStatus.BAD_REQUEST
                );
            }
        }
    }

    private int findCourseIndexById(int id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                return i;
            }
        }

        throw new ApiException(
                "Course bulunamadı",
                HttpStatus.NOT_FOUND
        );
    }
}