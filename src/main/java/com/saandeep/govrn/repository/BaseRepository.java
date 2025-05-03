package com.saandeep.govrn.repository;

import com.saandeep.govrn.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, U> extends JpaRepository<T, U> {
    @Override
    @Query("Select t from #{#entityName} t where t.deletedAt is null and t.id = :id")
    Optional<T> findById(@Param("id") U id);

    @Override
    @Query("Select t from #{#entityName} t where t.deletedAt is null")
    List<T> findAll();
}
