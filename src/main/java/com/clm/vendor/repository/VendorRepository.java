package com.clm.vendor.repository;

import com.clm.vendor.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
     List<Vendor> findByAppTypeId(Long appTypeId);
}
