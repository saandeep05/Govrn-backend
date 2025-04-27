package com.saandeep.govrn.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@Data
@Entity
public class Region extends BaseEntity {
    @Id
    @Column(name = "region_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String base;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String state;

    @Value("India")
    private String country;

    @Column(nullable = false)
    private String pinCode;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Person> users;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Project> projects;
}
