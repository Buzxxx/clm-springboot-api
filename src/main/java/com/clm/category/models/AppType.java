package com.clm.category.models;

import com.clm.vendor.models.Vendor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.OrderBy;

@Entity

@Getter @Setter @NoArgsConstructor
public class AppType {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String image;

    @OneToMany(mappedBy = "appType")
    @OrderBy("id ASC")
    private Set<SubType> subTypes = new HashSet<>();

    // One AppType has many Vendors
    @OneToMany(mappedBy = "appType")
    @OrderBy("id ASC")
    private Set<Vendor> vendors = new HashSet<>();

    // One AppType has many Categories
    @OneToMany(mappedBy = "appType")
    private Set<Category> categories = new HashSet<>();



    private LocalDateTime created_ts;
    private String created_by;
    private LocalDateTime last_updated_ts;
    private String last_updated_by;


}
