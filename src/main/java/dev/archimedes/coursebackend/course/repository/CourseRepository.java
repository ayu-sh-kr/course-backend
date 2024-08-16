package dev.archimedes.coursebackend.course.repository;

import dev.archimedes.coursebackend.course.dto.CourseFetchRecord;
import dev.archimedes.coursebackend.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("""
        select new dev.archimedes.coursebackend.course.dto.CourseFetchRecord(
            c.id, c.title, c.code, c.description, c.createTime
        )
        from Course c
    """)
    List<CourseFetchRecord> getAllCourse();

    CourseFetchRecord getCourseById(Integer id);

    @Query("""
        select case when count(c) > 0 then true else false end
        from Course c
        where lower(c.code) = lower(?1)
    """)
    boolean existsByCode(String code);

}
