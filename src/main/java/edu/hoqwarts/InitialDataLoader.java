package edu.hoqwarts;

import edu.hoqwarts.enums.EmpType;
import edu.hoqwarts.models.Course;
import edu.hoqwarts.models.House;
import edu.hoqwarts.models.Student;
import edu.hoqwarts.models.Teacher;
import edu.hoqwarts.repositories.CourseRepository;
import edu.hoqwarts.repositories.HouseRepository;
import edu.hoqwarts.repositories.StudentRepository;
import edu.hoqwarts.repositories.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;

@Component
public class InitialDataLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final HouseRepository houseRepository;

    public InitialDataLoader(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository, HouseRepository houseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("InitData is running");

        initializeHouses();
        initializeStudents();
        initializeTeachers();
        initializeCourses();
    }

    private void initializeHouses() {
        if (houseRepository.count() == 0) {
            houseRepository.saveAll(List.of(
                    new House("Gryffindor", "Godric Gryffindor", List.of("Red", "Gold")),
                    new House("Slytherin", "Salazar Slytherin", List.of("Green", "Silver")),
                    new House("Hufflepuff", "Helga Hufflepuff", List.of("Yellow", "Black")),
                    new House("Ravenclaw", "Rowena Ravenclaw", List.of("Blue", "Bronze"))
            ));
        }
    }


    private void initializeStudents() {
        if (studentRepository.count() == 0) {
            List<House> houses = houseRepository.findAll();

            List<Student> students = List.of(
                    new Student("Dennis", "Christon", "Helling", LocalDate.of(2016, 12, 12), houses.get(0), false, 1880, 1890, false),
                    new Student("Harry", "James", "Potter", LocalDate.of(1980, 6, 30), houses.get(0), true, 1991, 1998, false),
                    new Student("Ron", "Bilius", "Weasley", LocalDate.of(1980, 2, 1), houses.get(0), true, 1991, 1998, false),
                    new Student("Draco", "", "Malfoy", LocalDate.of(1980, 5, 5), houses.get(1), true, 1991, 1998, false),
                    new Student("Luna", "", "Lovegood", LocalDate.of(1981, 1, 13), houses.get(2), true, 1992, 1999, false),
                    new Student("Neville", "Frank", "Longbottom", LocalDate.of(1980, 6, 30), houses.get(2), true, 1991, 1998, false),
                    new Student("Ginevra", "Molly", "Weasley", LocalDate.of(1981, 7, 11), houses.get(1), true, 1992, 1999, false),
                    new Student("Fred", "", "Weasley", LocalDate.of(1978, 4, 1), houses.get(2), true, 1989, 1996, false),
                    new Student("Xenophilius", "", "Lovegood", LocalDate.of(1950, 12, 29), houses.get(3), true, 1961, 1968, false),
                    new Student("Cedric", "", "Diggory", LocalDate.of(1977, 8, 15), houses.get(3), true, 1989, 1995, true)
            );

            studentRepository.saveAll(students);
        }
    }

    private void initializeTeachers() {
        if (teacherRepository.count() == 0) {
            List<House> houses = houseRepository.findAll();

            List<Teacher> teachers = List.of(
                    new Teacher("Severus", "", "Snape", LocalDate.of(1960, 1, 9), houses.get(1), EmpType.TEMPORARY, LocalDate.of(1981, 9, 1), null),
                    new Teacher("Minerva", "", "McGonagall", LocalDate.of(1935, 10, 4), houses.get(0), EmpType.TENURED, LocalDate.of(1956, 9, 1), null),
                    new Teacher("Filius", "", "Flitwick", LocalDate.of(1930, 10, 17), houses.get(3), EmpType.DECEASED, LocalDate.of(1975, 9, 1), null),
                    new Teacher("Pomona", "", "Sprout", LocalDate.of(1941, 5, 15), houses.get(2), EmpType.DISCHARGED, LocalDate.of(1974, 9, 1), null),
                    new Teacher("Remus", "John", "Lupin", LocalDate.of(1960, 3, 10), houses.get(3), EmpType.PROBATION, LocalDate.of(1993, 9, 1), LocalDate.of(1994, 6, 18)),
                    new Teacher("Sybill", "Patricia", "Trelawney", LocalDate.of(1959, 3, 9), houses.get(1), EmpType.TENURED, LocalDate.of(1980, 9, 1), null),
                    new Teacher("Horace", "", "Slughorn", LocalDate.of(1913, 4, 28), houses.get(1), EmpType.TEMPORARY, LocalDate.of(1931, 9, 1), null),
                    new Teacher("Gilderoy", "", "Lockhart", LocalDate.of(1964, 1, 26), houses.get(0), EmpType.DECEASED, LocalDate.of(1984, 9, 1), null),
                    new Teacher("Dolores", "Jane", "Umbridge", LocalDate.of(1965, 8, 26), houses.get(1), EmpType.TEMPORARY, LocalDate.of(1990, 9, 1), LocalDate.of(1996, 6, 30)),
                    new Teacher("Rubeus", "", "Hagrid", LocalDate.of(1928, 12, 6), houses.get(0), EmpType.TENURED, LocalDate.of(1963, 9, 1), null)
            );

            teacherRepository.saveAll(teachers);
        }
    }

    private void initializeCourses() {
        if (courseRepository.count() == 0) {
            List<Teacher> teachers = teacherRepository.findAll();
            List<Student> students = studentRepository.findAll();

            Course course1 = new Course("Potions", 1, true);
            Course course2 = new Course("Transfiguration", 1, true);
            Course course3 = new Course("Charms", 1, true);

            // Set teacher for each course
            course1.setTeacher(teachers.get(0));
            course2.setTeacher(teachers.get(1));
            course3.setTeacher(teachers.get(2));

            // Assign students to courses
            course1.setStudents(students.subList(1, 4));
            course2.setStudents(students.subList(4, 7));
            course3.setStudents(students.subList(7, 10));

            List<Course> courses = List.of(course1, course2, course3);

            courseRepository.saveAll(courses);
        }
    }

}
