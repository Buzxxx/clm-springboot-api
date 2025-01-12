package com.clm.category.repository;

import com.clm.category.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.options WHERE c.id = :id")
    Optional<Category> findByIdWithOptions(@Param("id") Long id);

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.options WHERE c.id IN :ids")
    List<Category> findByIdsWithOptions(@Param("ids") Set<Long> ids);

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.options")
    List<Category> findAllWithOptions();
}


