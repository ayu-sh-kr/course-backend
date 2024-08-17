package dev.archimedes.coursebackend.delivery.service;

import dev.archimedes.coursebackend.application.exception.CourseException;
import dev.archimedes.coursebackend.course.service.CourseService;
import dev.archimedes.coursebackend.delivery.dto.DeliveryCreateRecord;
import dev.archimedes.coursebackend.delivery.dto.DeliveryFetchedRecord;
import dev.archimedes.coursebackend.delivery.entity.CourseDelivery;
import dev.archimedes.coursebackend.delivery.event.CourseDeleteEvent;
import dev.archimedes.coursebackend.delivery.repository.CourseDeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseDeliveryService {

    private final CourseDeliveryRepository courseDeliveryRepository;
    private final CourseService courseService;


    public CourseDelivery save(DeliveryCreateRecord record) {

        if(!courseService.existsById(record.courseId())) {
            throw new CourseException("Course does not exists", HttpStatus.BAD_REQUEST);
        }

        if(courseDeliveryRepository.existsByYearAndSemesterAndCourseId(record.year(), record.semester(), record.courseId())) {
            throw new CourseException("Instance Already Exists", HttpStatus.BAD_REQUEST);
        }

        CourseDelivery delivery = CourseDelivery.builder()
                .year(record.year())
                .semester(record.semester())
                .courseId(record.courseId())
                .build();

        return courseDeliveryRepository.save(delivery);
    }

    public List<DeliveryFetchedRecord> findAllInstances() {
        List<DeliveryFetchedRecord> records = courseDeliveryRepository.getAllFetchedRecord();

        if(records.isEmpty()) {
            throw new CourseException("No Instance Found", HttpStatus.NOT_FOUND);
        }

        return records;
    }

    public List<DeliveryFetchedRecord> findCoursesByYearAndSemester(String year, String semester) {
        List<DeliveryFetchedRecord> fetchRecords =  courseDeliveryRepository.getCourseDeliveriesByYearAndSemester(year, semester);

        if(fetchRecords.isEmpty()) {
            throw new CourseException("No Courses found for given search", HttpStatus.NOT_FOUND);
        }

        return fetchRecords;

    }


    public DeliveryFetchedRecord findCourseInstanceByYearSemesterAndCourseId(String year, String semester, Integer courseId) {
        if(!courseDeliveryRepository.existsByYearAndSemesterAndCourseId(year, semester, courseId)) {
            throw new CourseException("No instance exists", HttpStatus.NOT_FOUND);
        }

        return  courseDeliveryRepository.getCourseDeliveryByYearAndSemesterAndCourseId(year, semester, courseId);
    }

    public void deleteInstanceByYearSemesterAndCourseId(String year, String semester, Integer id) {
        if(!courseDeliveryRepository.existsByYearAndSemesterAndCourseId(year, semester, id)) {
            throw new CourseException("No Such Instance", HttpStatus.BAD_REQUEST);
        }

        courseDeliveryRepository.deleteCourseDeliveryByYearAndSemesterAndCourseId(year, semester, id);
    }

    @Async
    @EventListener
    public void handleCourseDelete(CourseDeleteEvent event) {
        courseDeliveryRepository.deleteCourseDeliveriesByCourseId(event.courseId());
    }

}
