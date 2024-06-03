package edu.hoqwarts.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;
    private String founder;

    @ElementCollection
    private List<String> colors;

    public House() {
    }

    public House(String name, String founder, List<String> colors) {
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }
}
