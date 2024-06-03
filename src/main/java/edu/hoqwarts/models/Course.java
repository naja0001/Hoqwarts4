package edu.hoqwarts.models;

import jakarta.persistence.*;

import java.util.List;
import lombok.*;

@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private int schoolYear;
    private boolean current;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    // Constructors
    public Course() {
    }

    public Course(String subject, int schoolYear, boolean current) {
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.current = current;
    }
}
