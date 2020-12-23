package com.cybertek;

import com.cybertek.repository.EmployeeRepository;
import com.cybertek.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DerivedqueryApplication {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(DerivedqueryApplication.class, args);
    }

    @PostConstruct
    public void testRegions(){
        System.out.println("------------------ Region start --------------------");
        System.out.println("findByCountry: " + regionRepository.findByCountry("Canada"));
        System.out.println("findDistinctByCountry: " + regionRepository.findDistinctByCountry("Canada"));
        System.out.println("findByCountryContaining: " + regionRepository.findByCountryContaining("United"));
        System.out.println("findByCountryContainingOrderByCountry: " +
                regionRepository.findByCountryContainingOrderByCountry("United"));
        System.out.println("findTop2ByCountry: " + regionRepository.findTop2ByCountry("United"));

        System.out.println("------------------ Region end --------------------");
    }

    @PostConstruct
    public void testEmployees(){
        System.out.println("------------------ Employee start --------------------");

        System.out.println("findByEmail()" + employeeRepository.findByEmail("bmanueau0@dion.ne.jp"));
        System.out.println("findByFirstNameAndLastNameOrEmail: " +
                employeeRepository.findByFirstNameAndLastNameOrEmail("name","sdad","email"));
        System.out.println("findByFirstNameStartingWith: " +
                employeeRepository.findByFirstNameStartingWith("J"));
        System.out.println("findByEmailIsNull()" + employeeRepository.findByEmailIsNull());

        System.out.println("------------------ Employee end --------------------");
    }

}
