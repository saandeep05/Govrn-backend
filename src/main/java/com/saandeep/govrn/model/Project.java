package com.saandeep.govrn.model;

import com.saandeep.govrn.util.enums.ProjectStatus;
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

    private String name;

    private String description;

    private LocalDateTime proposedDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "project")
    private List<ProjectStatusMetaData> statusMetaData;

    @ManyToMany
    @JoinTable(name = "project_managed",
    joinColumns = @JoinColumn(name = "project_id"),
    inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> managers;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    private Integer daysInStage;
}
