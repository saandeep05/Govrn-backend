package com.saandeep.govrn.repository;

import com.saandeep.govrn.model.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Project p set p.startDate = :startDate where p.id in :projectIdList")
    void updateStartDate(@Param("startDate") LocalDateTime startDate, @Param("projectIdList") List<Long> projectIdList);
}
