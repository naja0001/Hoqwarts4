package edu.hoqwarts.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_name", referencedColumnName = "name")
    private House house;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    private boolean prefect;
    private int ey;
    private int gy;
    private boolean graduated;

    public Student() {
        // Default constructor
    }

    public Student(String firstName,
                   String middleName,
                   String lastName,
                   LocalDate dob,
                   House house,
                   boolean prefect,
                   int ey,
                   int gy,
                   boolean graduated) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.house = house;
        this.prefect = prefect;
        this.ey = ey;
        this.gy = gy;
        this.graduated = graduated;
    }
}
