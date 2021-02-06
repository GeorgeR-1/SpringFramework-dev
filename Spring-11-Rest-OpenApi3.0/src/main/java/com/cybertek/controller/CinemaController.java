package com.cybertek.controller;

import com.cybertek.entity.Cinema;
import com.cybertek.repository.CinemaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cinema")
@Tag(name = "Cinema",description = "Cinema API")
public class CinemaController {

    @Autowired
    private CinemaRepository cinemaRepository;

    @GetMapping("/read-all")
    @Operation(summary = "Read all cinemas")
    public List<Cinema> readAll(){
        return cinemaRepository.findAll();
    }

    @PostMapping("/create-cinema")
    public Cinema createCinema(@RequestBody Cinema cinema){
        return cinemaRepository.save(cinema);
    }

    @PutMapping("/update/{id}")
    public Cinema updateCinema(@PathVariable("id") Long id, @RequestBody Cinema cinema){
        Cinema foundCinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("This cinema does not exist"));

        foundCinema.setLocation(cinema.getLocation());
        foundCinema.setName(cinema.getName());
        foundCinema.setLocation(cinema.getLocation());

        return cinemaRepository.save(foundCinema);
    }

    @DeleteMapping("/delete/{id}")
    public List<Cinema> deleteCinema(@PathVariable("id") Long id){

        cinemaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cinema does not exist"));

        cinemaRepository.deleteById(id);

        return cinemaRepository.findAll();
    }



}
