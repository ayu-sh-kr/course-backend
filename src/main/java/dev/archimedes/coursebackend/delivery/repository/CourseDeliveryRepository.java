package dev.archimedes.coursebackend.delivery.repository;

import dev.archimedes.coursebackend.course.dto.CourseFetchRecord;
import dev.archimedes.coursebackend.delivery.dto.DeliveryFetchedRecord;
import dev.archimedes.coursebackend.delivery.entity.CourseDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDeliveryRepository extends JpaRepository<CourseDelivery, Integer> {

    List<DeliveryFetchedRecord> getCourseDeliveriesByYearAndSemester(String year, String semester);

    boolean existsByYearAndSemesterAndCourseId(String year, String semester, Integer courseId);

    DeliveryFetchedRecord getCourseDeliveryByYearAndSemesterAndCourseId(String year, String semester, Integer courseId);

    void deleteCourseDeliveryByYearAndSemesterAndCourseId(String year, String semester, Integer courseId);

    void deleteCourseDeliveriesByCourseId(Integer courseId);
}
