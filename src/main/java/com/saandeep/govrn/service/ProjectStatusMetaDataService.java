package com.saandeep.govrn.service;

import com.saandeep.govrn.model.ProjectStatusMetaData;
import com.saandeep.govrn.repository.ProjectStatusMetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectStatusMetaDataService {
    @Autowired
    private ProjectStatusMetaDataRepository projectStatusMetaDataRepository;

    public List<ProjectStatusMetaData> getStatusHistoryForProject(Long projectId) {
        return projectStatusMetaDataRepository.findByProjectId(projectId);
    }
}
