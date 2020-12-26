package com.cybertek.repository;

import com.cybertek.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieCinemaRepository extends JpaRepository<MovieCinema,Long> {

    // ------------------- DERIVED QUERIES ------------------- //
//Write a derived query to read movie cinema with id
    Optional<MovieCinema> findMovieCinemaById(Long id);
//Write a derived query to count all movie cinemas with a specific cinema id
    Integer countAllByCinemaId(Long id);
//Write a derived query to count all movie cinemas with a specific movie id
    Integer countAllByMovieId(Long id);
//Write a derived query to list all movie cinemas with higher
// than a specific date
    List<MovieCinema> findAllByDateTimeAfter(LocalDateTime dateTime);
//Write a derived query to find the top 3 expensive movies
    List<MovieCinema> findFirst3byOrderByMoviePriceDesc();
//Write a derived query to list all movie cinemas that contains
// a specific movie name
    List<MovieCinema> findAllByMovieNameContaining(String pattern);
//Write a derived query to list all movie cinemas in a specific location
    List<MovieCinema> findAllByCinemaLocationName(String name);

    // ------------------- JPQL QUERIES ------------------- //
//Write a JPQL query to list all movie cinemas with higher than a specific date
    @Query("SELECT mc FROM MovieCinema mc where mc.dateTime = ?1")
    List<MovieCinema> findAfterSpecificDate(LocalDateTime dateTime);

    // ------------------- Native QUERIES ------------------- //
//Write a native query to count all movie cinemas by cinema id
    @Query(value = "SELECT * FROM movie_cinema where id = :id",
            nativeQuery = true)
    List<MovieCinema> returnMovieCinemaById(Long id);
//Write a native query that returns all movie cinemas by location name
    @Query(value = "SELECT mc.name FROM movie_cinema mc join location l " +
            "on mc.id = l.id where l.name = ?1 ",nativeQuery = true)
    List<MovieCinema> returnMovieCinemasByLocationName(String locationName);

}
