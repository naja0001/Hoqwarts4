package edu.hoqwarts.controllers;

import edu.hoqwarts.models.Course;
import edu.hoqwarts.services.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseServices courseServices;

    @GetMapping
    public List<Course> getCourses() {
        return courseServices.getCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseServices.getCourseById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseServices.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseServices.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseServices.deleteCourse(id);
    }
}
