package com.saandeep.govrn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.saandeep.govrn.util.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person extends BaseEntity {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String aadhaarNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private UserRole role;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    @JsonManagedReference
    private Region region;

    @ManyToMany(mappedBy = "managers")
    @JsonBackReference
    private Set<Project> projects;
}
