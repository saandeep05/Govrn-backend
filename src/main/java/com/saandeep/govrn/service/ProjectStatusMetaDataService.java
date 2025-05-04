package com.saandeep.govrn.service;

import com.saandeep.govrn.model.Project;
import com.saandeep.govrn.model.ProjectStatusMetaData;
import com.saandeep.govrn.repository.ProjectStatusMetaDataRepository;
import com.saandeep.govrn.util.enums.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectStatusMetaDataService {
    @Autowired
    private ProjectStatusMetaDataRepository projectStatusMetaDataRepository;

    public List<ProjectStatusMetaData> getStatusHistoryForProject(Long projectId) {
        return projectStatusMetaDataRepository.findByProjectId(projectId);
    }

    public Project createAndAddStatusMetaData(Project project, ProjectStatus projectStatus) {
        ProjectStatusMetaData statusMetaData = new ProjectStatusMetaData(
                null,
                projectStatus,
                project,
                LocalDateTime.now()
        );

        statusMetaData = projectStatusMetaDataRepository.save(statusMetaData);

        if(project.getStatusMetaData() == null) {
            project.setStatusMetaData(new ArrayList<>());
        }
        project.getStatusMetaData().add(statusMetaData);

        return project;
    }
}
