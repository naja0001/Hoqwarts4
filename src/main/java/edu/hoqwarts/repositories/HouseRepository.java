package edu.hoqwarts.repositories;

import edu.hoqwarts.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, String> {

    Optional<House> findByName(String name);
}
