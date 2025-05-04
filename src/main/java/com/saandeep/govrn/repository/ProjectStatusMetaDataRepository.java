package com.saandeep.govrn.repository;

import com.saandeep.govrn.model.ProjectStatusMetaData;

import java.util.List;

public interface ProjectStatusMetaDataRepository extends BaseRepository<ProjectStatusMetaData, Long> {
    List<ProjectStatusMetaData> findByProjectId(Long projectId);
}
