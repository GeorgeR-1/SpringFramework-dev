package com.cybertek;

import com.cybertek.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JpqlApplication {

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpqlApplication.class, args);
    }

    @PostConstruct
    public void testEmployee(){
        System.out.println("getEmployeeDetail: "+employeeRepository.getEmployeeDetail());
        System.out.println("getEmployeeSalary: "+employeeRepository.getEmployeeSalary());
        System.out.println("getEmployeeByEmail: "+
                employeeRepository.getEmployeeByEmail("myakovlivf@ucsd.edu"));
        System.out.println("getEmployeeByEmailAndSalary: "+
                employeeRepository.getEmployeeByEmailAndSalary("myakovlivf@ucsd.edu",78141));
        employeeRepository.updateEmployeeJPQL(1);

    }



}
