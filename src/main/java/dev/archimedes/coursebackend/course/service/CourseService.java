package dev.archimedes.coursebackend.course.service;

import dev.archimedes.coursebackend.application.exception.CourseException;
import dev.archimedes.coursebackend.course.dto.CourseCreateRecord;
import dev.archimedes.coursebackend.course.dto.CourseFetchRecord;
import dev.archimedes.coursebackend.course.entity.Course;
import dev.archimedes.coursebackend.course.repository.CourseRepository;
import dev.archimedes.coursebackend.delivery.event.CourseDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final ApplicationEventPublisher eventPublisher;

    public Course save(CourseCreateRecord record) {

        if(courseRepository.existsByCode(record.code())) {
            throw new CourseException("Course exists with code", HttpStatus.BAD_REQUEST);
        }

        Course course = Course.builder()
                .title(record.title())
                .code(record.code())
                .description(record.description())
                .build();
        return courseRepository.save(course);
    }


    public List<CourseFetchRecord> getAllCourse() {
        return courseRepository.getAllCourse();
    }

    public CourseFetchRecord findById(Integer id) {

        if(!existsById(id)) {
            throw new CourseException(
                    "Course Does Not Exists",
                    HttpStatus.NOT_FOUND
            );
        }

        return courseRepository.getCourseById(id);
    }

    public void deleteCourseById(Integer id) {
        if(existsById(id)) {
            courseRepository.deleteById(id);
            eventPublisher.publishEvent(
                    new CourseDeleteEvent(id)
            );
            return;
        }

        throw new CourseException(
                "Course Does Not Exists",
                HttpStatus.NOT_FOUND
        );
    }

    public boolean existsById(Integer courseId) {
        return courseRepository.existsById(courseId);
    }
}
