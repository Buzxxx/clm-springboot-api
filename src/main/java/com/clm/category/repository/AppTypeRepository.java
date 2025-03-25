package com.clm.category.repository;

import com.clm.category.models.AppType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface AppTypeRepository extends JpaRepository<AppType, Long> {

    @Query("SELECT DISTINCT a FROM AppType a " +
            "LEFT JOIN FETCH a.subTypes st " +
            "LEFT JOIN FETCH st.categories c " +
            "LEFT JOIN FETCH c.options " +
            "WHERE a.id = :id")
    Optional<AppType> findByIdWithSubTypesAndCategoriesAndOptions(@Param("id") Long id);
}
