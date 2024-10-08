package dev.archimedes.coursebackend.delivery.controller;

import dev.archimedes.coursebackend.application.api.APIResponse;
import dev.archimedes.coursebackend.delivery.dto.DeliveryCreateRecord;
import dev.archimedes.coursebackend.delivery.dto.DeliveryFetchedRecord;
import dev.archimedes.coursebackend.delivery.entity.CourseDelivery;
import dev.archimedes.coursebackend.delivery.service.CourseDeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instances")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.OPTIONS})
public class CourseDeliveryController {


    private final CourseDeliveryService courseDeliveryService;

    public CourseDeliveryController(CourseDeliveryService courseDeliveryService) {
        this.courseDeliveryService = courseDeliveryService;
    }

    @PostMapping("/")
    public ResponseEntity<APIResponse<?>> create(@RequestBody @Valid DeliveryCreateRecord record) {
        System.out.println(record);
        CourseDelivery delivery = courseDeliveryService.save(record);

        return new ResponseEntity<>(
                APIResponse.ofBody(
                        "Course Saved Successfully",
                        new DeliveryFetchedRecord(
                                delivery.getId(), delivery.getYear(), delivery.getSemester(), delivery.getCourseId(), delivery.getCreateTime()
                        )
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/")
    public ResponseEntity<APIResponse<List<DeliveryFetchedRecord>>> findAllInstance() {
        List<DeliveryFetchedRecord> records = courseDeliveryService.findAllInstances();
        return new ResponseEntity<>(
                APIResponse.ofBody(
                        "Fetched Instances",
                        records
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{year}/{semester}")
    public ResponseEntity<APIResponse<List<DeliveryFetchedRecord>>> getByYearAndSemester(@PathVariable("year") String year, @PathVariable("semester") String semester) {
        return new ResponseEntity<>(
                APIResponse.ofBody(
                        "Course Instance Found",
                        courseDeliveryService.findCoursesByYearAndSemester(year, semester)
                ),
                HttpStatus.OK
        );
    }


    @GetMapping("/{year}/{semester}/{id}")
    public ResponseEntity<APIResponse<DeliveryFetchedRecord>> getByCourseAndSemesterAndYear(
            @PathVariable("year") String year, @PathVariable("semester") String semester, @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(
                APIResponse.ofBody(
                        "Instance Fetched Successfully",
                        courseDeliveryService.findCourseInstanceByYearSemesterAndCourseId(year, semester, id)
                ),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/delete/{year}/{semester}/{id}")
    public ResponseEntity<APIResponse<?>> deleteInstanceByCourseIdAndYearAndSemester(
            @PathVariable("year") String year, @PathVariable("semester") String semester, @PathVariable("id") Integer id
    ) {
        courseDeliveryService.deleteInstanceByYearSemesterAndCourseId(year, semester, id);
        return new ResponseEntity<>(
                APIResponse.ofMessage("Instance deleted successfully"),
                HttpStatus.OK
        );
    }


}
