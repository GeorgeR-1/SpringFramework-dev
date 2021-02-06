package com.cybertek.entity;

import com.cybertek.enums.UserRole;
import com.cybertek.enums.UserState;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.print.DocFlavor;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "id_deleted=false")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Boolean isVerified;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserState state;



}
