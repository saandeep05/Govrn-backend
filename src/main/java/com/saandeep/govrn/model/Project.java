package com.saandeep.govrn.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Project extends BaseEntity {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime proposedDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "project")
    private List<ProjectStatusMetaData> statusMetaData;

    @ManyToMany
    @JoinTable(name = "project_managed",
    joinColumns = @JoinColumn(name = "project_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "person_id", nullable = false))
    private Set<Person> managers;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}
