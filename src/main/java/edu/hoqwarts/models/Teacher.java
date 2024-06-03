package edu.hoqwarts.models;

import edu.hoqwarts.enums.EmpType;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Teacher {
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


    @OneToMany(mappedBy = "teacher",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Course> courses;

    private boolean headOfHouse;
    private EmpType employment;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;

    // Constructors
    public Teacher() {
        // Default constructor
    }

    public Teacher(String firstName,
                   String middleName,
                   String lastName,
                   LocalDate dob,
                   House house,
                   EmpType employment,
                   LocalDate employmentStart,
                   LocalDate employmentEnd) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.house = house;
        this.headOfHouse = headOfHouse;
        this.employment = employment;
        this.employmentStart = employmentStart;
        this.employmentEnd = employmentEnd;
    }
}
