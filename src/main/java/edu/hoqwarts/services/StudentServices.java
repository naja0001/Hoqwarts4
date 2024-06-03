package edu.hoqwarts.services;

import edu.hoqwarts.models.Student;
import edu.hoqwarts.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Read
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Update
    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(updatedStudent.getFirstName());
        student.setMiddleName(updatedStudent.getMiddleName());
        student.setLastName(updatedStudent.getLastName());
        student.setDob(updatedStudent.getDob());
        student.setHouse(updatedStudent.getHouse());
        student.setPrefect(updatedStudent.isPrefect());
        student.setEy(updatedStudent.getEy());
        student.setGy(updatedStudent.getGy());
        student.setGraduated(updatedStudent.isGraduated());

        return studentRepository.save(student);
    }

    // Delete
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
