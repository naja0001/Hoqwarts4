package edu.hoqwarts.controllers;

import edu.hoqwarts.services.TeacherServices;
import edu.hoqwarts.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherServices teacherServices;

    @Autowired
    public TeacherController(TeacherServices teacherServices) {
        this.teacherServices = teacherServices;
    }

    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherServices.getTeachers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherServices.getTeacherById(id);

        return teacher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherServices.createTeacher(teacher);
    }


    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        return teacherServices.updateTeacher(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherServices.deleteTeacher(id);
    }
}
