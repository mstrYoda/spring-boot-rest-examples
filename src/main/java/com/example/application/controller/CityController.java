package com.example.application.controller;

import com.example.domain.city.City;
import com.example.infrastructure.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RepositoryRestController
public class CityController {

    CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping(path = "/city")
    public ResponseEntity getCity(@RequestParam("id") Optional<Integer> id) {
        if (!id.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Optional<City> city = cityRepository.findById(id.get());

        return ResponseEntity.ok(city);
    }
}
