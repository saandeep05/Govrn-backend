package com.saandeep.govrn.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project extends BaseEntity {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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
    @JsonManagedReference
    private List<ProjectStatusMetaData> statusMetaData;

    @ManyToMany
    @JoinTable(name = "project_managed",
    joinColumns = @JoinColumn(name = "project_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "person_id", nullable = false))
    @JsonManagedReference
    private Set<Person> managers;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    @JsonManagedReference
    private Region region;
}
