package com.saandeep.govrn.repository;

import com.saandeep.govrn.model.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> {
}
