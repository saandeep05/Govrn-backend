package com.saandeep.govrn.repository;

import com.saandeep.govrn.model.ProjectStatusMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectStatusMetaDataRepository extends JpaRepository<ProjectStatusMetaData, Long> {
    List<ProjectStatusMetaData> findByProjectId(Long projectId);
}
