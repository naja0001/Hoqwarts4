package edu.hoqwarts.services;

import edu.hoqwarts.models.Course;
import edu.hoqwarts.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServices {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServices(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Create
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Read
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Update
    // Update
    public Course updateCourse(Long id, Course updatedCourse) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setSubject(updatedCourse.getSubject());
        course.setSchoolYear(updatedCourse.getSchoolYear());
        course.setCurrent(updatedCourse.isCurrent());
        course.setStudents(updatedCourse.getStudents());

        return courseRepository.save(course);
    }

    // Delete
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
