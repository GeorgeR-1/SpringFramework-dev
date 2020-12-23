package com.cybertek.repository;

import com.cybertek.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,String> {

    //Display all departments in the Furniture departments
    List<Department> findByDepartment(String department);

    //Display all departments departments in the Health Division
    List<Department> findByDivision(String division);

    //Display all departments departments in the Health Division
    List<Department> findByDivisionIs(String division);

    //Display all departments departments in the Health Division
    List<Department> findByDivisionIsEquals(String division);

    //Display all departments with division name ends with 'ics'
    List<Department> findByDivisionEndingWith(String pattern);

    //Display top3 departments with division name includes 'Hea', without duplicates
    List<Department> findDistinctByTop3ByDivisionContains(String pattern);


}
