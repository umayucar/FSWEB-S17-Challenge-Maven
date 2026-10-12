package com.workintech.spring17challenge.validation;

import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.model.Course;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CourseValidator {

    public void validate(Course course) {

        if (course == null) {
            throw new ApiException(
                    "Course boş olamaz",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (course.getName() == null || course.getName().isBlank()) {
            throw new ApiException(
                    "Course name boş olamaz",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (course.getCredit() < 0 || course.getCredit() > 4) {
            throw new ApiException(
                    "Credit 0 ile 4 arasında olmalıdır",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (course.getGrade() == null) {
            throw new ApiException(
                    "Grade boş olamaz",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}