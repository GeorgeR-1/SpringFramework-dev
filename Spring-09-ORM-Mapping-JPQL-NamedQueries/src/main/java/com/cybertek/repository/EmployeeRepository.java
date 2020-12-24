package com.cybertek.repository;

import com.cybertek.entity.Department;
import com.cybertek.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT e FROM Employee e where e.email = 'dtrail8@tamu.edu'")
    Employee getEmployeeDetail();

    @Query("select e.salary from Employee e where e.email = 'dtrail8@tamu.edu'")
    Integer getEmployeeSalary();

    //single bind parameter
    @Query("select e from Employee e where e.email = ?1 ")
    Optional<Employee> getEmployeeByEmail(String email);

    //multiple bind parameter
    @Query("select e from Employee e where e.email = ?1 and  e.salary = ?2 ")
    Optional<Employee> getEmployeeByEmailAndSalary(String email, int salary);

    //single named parameter
    @Query("select e from Employee e where e.salary=:salary")
    Employee getEmployeeBySalary(@Param("salary") int salary);

    //multiple parameters
    @Query("select e from Employee e where e.firstName=:name or e.salary=:salary")
    List<Employee> getEmployeeByFirstNameOrSalary(@Param("name") String firstName,
                                                  @Param("salary") int salary);

    //Not equal
    @Query("select e from Employee e where e.salary <> ?1")
    List<Employee> getEmployeeBySalaryNotEqual(int salary);

    //Like / Contains / StartsWith / Ends With
    @Query("select e from Employee e where e.firstName like ?1 ")
    List<Employee> getEmployeeByFirstNameLike(String pattern);

    //Less Than
    @Query("SELECT e FROM Employee e WHERE e.salary < ?1")
    List<Employee> getEmployeeBySalaryLessThan(int salary);

    //Greater Than
    @Query("SELECT e FROM Employee e WHERE e.salary > ?1")
    List<Employee> getEmployeeBySalaryGreaterThan(int salary);

    //Between
    @Query("select e from Employee e where e.salary between ?1 and ?2")
    List<Employee> getEmployeeBySalaryBetween(int salary1, int salary2);

    //Before
    @Query("select e from Employee e where e.hireDate < ?1")
    List<Employee> getEmployeeByHireDateBefore(LocalDate date);

    //Null
    @Query("select e from Employee e where e.email is null ")
    List<Employee> getEmployeeByEmailIsNull();

    //Not Null
    @Query("select e from Employee e where e.email is not null ")
    List<Employee> getEmployeeByEmailIsNotNull();

    //Sort Salary in ascending order
    @Query("select e from Employee e order by e.salary")
    List<Employee> getEmployeeBySalaryOrderByAsc();

    //Sort Salary in descending order
    @Query("select e from Employee e order by e.salary desc ")
    List<Employee> getEmployeeBySalaryOrderBySalaryDesc();

    //Native Query
    @Query(value = "select * from employees where salary = ?1", nativeQuery = true)
    List<Employee> readEmployeeBySalary(int salary);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e set e.email = 'admin@email.com' where e.id =:id")
    void updateEmployeeJPQL(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET email='admin@email.com' where id=:id",nativeQuery = true)
    void updateEmployeeNativeQuery(@Param("id") Integer id);

    //Named Query
    List<Employee> retrieveEmployeeSalaryGreaterThan(Integer salary);



}
