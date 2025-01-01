package com.clm.category.repository;

import com.clm.category.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByCategoryId(Long categoryId);
}