package com.cybertek.entity;

import com.cybertek.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseEntity{

    private String first_name;
    private String last_name;
    private String email;
    @Column(columnDefinition = "DATE")
    private LocalDate hire_date;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int salary;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    public Employee(String first_name, String last_name, String email, LocalDate hire_date,
                    Gender gender, int salary) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.hire_date = hire_date;
        this.gender = gender;
        this.salary = salary;
    }



}
