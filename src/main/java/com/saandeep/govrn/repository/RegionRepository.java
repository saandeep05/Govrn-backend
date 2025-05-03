package com.saandeep.govrn.repository;

import com.saandeep.govrn.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends BaseRepository<Region, Long> {
}
