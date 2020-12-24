package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "regions")
@NoArgsConstructor
@ToString
public class Region extends BaseEntity{

    private String region;
    private String country;


}
