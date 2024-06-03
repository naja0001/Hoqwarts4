package edu.hoqwarts.services;

import edu.hoqwarts.models.House;
import edu.hoqwarts.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseServices {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseServices(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    // Read
    public List<House> getHouses() {
        return houseRepository.findAll();
    }

    public Optional<House> getHouseByName(String name) {
        return houseRepository.findByName(name);
    }

}
