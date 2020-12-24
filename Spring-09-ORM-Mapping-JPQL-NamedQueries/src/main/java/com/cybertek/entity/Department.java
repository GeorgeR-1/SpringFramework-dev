package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "departments")
@NoArgsConstructor
@NamedQuery(name = "Department.findOzzyDepartment",
            query = "SELECT d FROM Department d where d.division=?1")
@NamedNativeQuery(name = "Department.countAllDepartments",
                query = "SELECT * FROM departments",
                resultClass = Department.class)
public class Department{

    @Id
    private String department;
    private String division;





}
