package com.saandeep.govrn.model;

import com.saandeep.govrn.util.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProjectStatusMetaData {
    @Id
    @Column(name = "project_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ProjectStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
