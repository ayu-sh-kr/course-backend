package dev.archimedes.coursebackend.course.controller;

import dev.archimedes.coursebackend.application.api.APIResponse;
import dev.archimedes.coursebackend.course.dto.CourseCreateRecord;
import dev.archimedes.coursebackend.course.dto.CourseFetchRecord;
import dev.archimedes.coursebackend.course.entity.Course;
import dev.archimedes.coursebackend.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<APIResponse<CourseFetchRecord>> create(CourseCreateRecord record) {
        Course course = courseService.save(record);
        return new ResponseEntity<>(
                APIResponse.ofBody(
                    "Course Saved Successfully",
                        new CourseFetchRecord(
                                course.getId(), course.getTitle(), course.getCode(), course.getDescription(), course.getCreateTime()
                        )
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/")
    public ResponseEntity<APIResponse<List<CourseFetchRecord>>> getAll() {
        return new ResponseEntity<>(
                APIResponse.ofBody(
                        "Courses Fetched",
                        courseService.getAllCourse()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<APIResponse<CourseFetchRecord>> getOne(@PathVariable("courseId") int courseId) {
        return new ResponseEntity<>(
                APIResponse.ofBody(
                        "Single Course Fetched",
                        courseService.findById(courseId)
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<APIResponse<?>> deleteCourse(@PathVariable("courseId") int courseId) {
        courseService.deleteCourseById(courseId);
        return new ResponseEntity<>(
                APIResponse.ofMessage("Course Deleted Successfully"),
                HttpStatus.OK
        );
    }

}
