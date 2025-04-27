package com.saandeep.govrn.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Region extends BaseEntity {
    @Id
    @Column(name = "region_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String base;

    private String district;

    private String state;

    private String country;

    private String pinCode;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Person> users;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Project> projects;
}
