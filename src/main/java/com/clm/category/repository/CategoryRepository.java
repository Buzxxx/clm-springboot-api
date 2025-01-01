package com.clm.category.repository;

import com.clm.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.options WHERE c.id = :id")
    Optional<Category> findByIdWithOptions(@Param("id") Long id);
}


