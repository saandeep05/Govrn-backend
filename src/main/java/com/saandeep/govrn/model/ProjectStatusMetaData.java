package com.saandeep.govrn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.saandeep.govrn.util.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "project")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProjectStatusMetaData extends BaseEntity {
    @Id
    @Column(name = "project_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private ProjectStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    private Project project;

    private LocalDateTime startedAt;
}
