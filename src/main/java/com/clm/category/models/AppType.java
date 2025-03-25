package com.clm.category.models;

import com.clm.vendor.models.Vendor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity

@Getter @Setter @NoArgsConstructor
public class AppType {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "appType")
    private List<SubType> subTypes = new ArrayList<>();

    // One AppType has many Vendors
    @OneToMany(mappedBy = "appType")
    private List<Vendor> vendors = new ArrayList<>();

    // One AppType has many Categories
    @OneToMany(mappedBy = "appType")
    private List<Category> categories = new ArrayList<>();



    private LocalDateTime created_ts;
    private String created_by;
    private LocalDateTime last_updated_ts;
    private String last_updated_by;


}
