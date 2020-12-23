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
public class Department{

    @Id
    private String department;
    private String division;





}
