package com.saandeep.govrn.repository;

import com.saandeep.govrn.model.ProjectStatusMetaData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectStatusMetaDataRepository extends BaseRepository<ProjectStatusMetaData, Long> {
    List<ProjectStatusMetaData> findByProjectId(Long projectId);

    @Query("select psmd from ProjectStatusMetaData psmd" +
            " where psmd.id = :projectId" +
            " order by psmd.startedAt desc" +
            " limit 1")
    ProjectStatusMetaData findLatestStageData(@Param("projectId") Long projectId);
}
