package edu.hoqwarts.controllers;

import edu.hoqwarts.models.House;
import edu.hoqwarts.services.HouseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/houses")
public class HouseController {

    private final HouseServices houseServices;

    @Autowired
    public HouseController(HouseServices houseServices) {
        this.houseServices = houseServices;
    }

    // Get all houses
    @GetMapping
    public ResponseEntity<List<House>> getAllHouses() {
        List<House> houses = houseServices.getHouses();
        if (!houses.isEmpty()) {
            return ResponseEntity.ok(houses);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get house by name
    @GetMapping("/{name}")
    public ResponseEntity<?> getHouseByName(@PathVariable String name) {
        Optional<House> house = houseServices.getHouseByName(name);

        if (house.isPresent()) {
            return ResponseEntity.ok(house.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
