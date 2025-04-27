package com.saandeep.govrn.model;

import com.saandeep.govrn.util.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Person extends BaseEntity {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aadhaarNumber;

    private String email;

    private String password;

    private UserRole role;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToMany(mappedBy = "managers")
    private Set<Project> projects;
}
