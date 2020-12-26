package com.cybertek.repository;

import com.cybertek.entity.Cinema;
import com.cybertek.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository extends JpaRepository<Cinema,Long> {
    // ------------------- DERIVED QUERIES ------------------- //
//Write a derived query to get cinema with a specific name
    Optional<Cinema> findByName(String name);
//Write a derived query to read sorted the top 3 cinemas that
// contains a specific sponsored name
    Optional<Cinema> findFirst3ByNameContaining(String pattern);
//Write a derived query to list all cinemas in a specific country
    Optional<Cinema> findAllByLocation_Country(String country);
//Write a derived query to list all cinemas with a specific
// name or sponsored name
    Optional<Cinema> findAllByNameOrSponsoredName(String name,String sponsoredName);

    // ------------------- JPQL QUERIES ------------------- //
//Write a JPQL query to read the cinema name with a specific id
    @Query("SELECT c.name FROM Cinema c WHERE c.id =:id")
    String findCinemaById(Long id);
    // ------------------- Native QUERIES ------------------- //
//Write a native query to read all cinemas by location country
    @Query(value = "SELECT * FROM cinema c inner join location l " +
            "on l.id =c.id where l.country=?1",
            nativeQuery = true)
    List<Cinema> fetchCinemasByLocationCountry(String locationCountry);
//Write a native query to read all cinemas by name or
// sponsored name contains a specific pattern
    @Query(value = "SELECT * FROM cinema where " +
            "name ilike concat('%',:pattern,'%') or " +
            "sponsored_name ilike concat('%',:pattern,'%')",
            nativeQuery = true)
    List<Cinema> retrieveAllCinemasByNameOrSponsoredName(String pattern);

//Write a native query to sort all cinemas by name

//Write a native query to distinct all cinemas by sponsored name

}
