package com.cybertek.entity;


import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Id
    private Integer id;
}
