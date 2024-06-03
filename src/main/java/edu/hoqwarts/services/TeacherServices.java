package edu.hoqwarts.services;

import edu.hoqwarts.models.Teacher;
import edu.hoqwarts.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServices {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServices(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Create
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Read
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    // Update
    // Update
    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setFirstName(updatedTeacher.getFirstName());
        teacher.setMiddleName(updatedTeacher.getMiddleName());
        teacher.setLastName(updatedTeacher.getLastName());
        teacher.setHeadOfHouse(updatedTeacher.isHeadOfHouse()); // Set headOfHouse

        return teacherRepository.save(teacher);
    }

    // Delete
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
