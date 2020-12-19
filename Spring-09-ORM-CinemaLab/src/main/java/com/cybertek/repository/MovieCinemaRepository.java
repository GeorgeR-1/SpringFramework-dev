package com.cybertek.repository;

import com.cybertek.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCinemaRepository extends JpaRepository<MovieCinema,Long> {
}
