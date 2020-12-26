package com.cybertek;

import com.cybertek.repository.CinemaRepository;
import com.cybertek.repository.MovieCinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CinemaappApplication {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    MovieCinemaRepository movieCinemaRepository;

    public static void main(String[] args) {
        SpringApplication.run(CinemaappApplication.class, args);
    }

    @PostConstruct
    public void runner(){
        System.out.println(movieCinemaRepository.returnMovieCinemasByLocationName("AMC Empire 25"));

    }
}
