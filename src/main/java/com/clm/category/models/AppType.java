package com.clm.category.models;

import com.clm.vendor.models.Vendor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity

@Getter @Setter @NoArgsConstructor
@Builder
@AllArgsConstructor
public class AppType {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String image;

    @OneToMany(mappedBy = "appType", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<SubType> subTypes = new HashSet<>();

    // One AppType has many Vendors
    @OneToMany(mappedBy = "appType")
    @OrderBy("id ASC")
    private Set<Vendor> vendors = new HashSet<>();
    
    private LocalDateTime created_ts;
    private String created_by;
    private LocalDateTime last_updated_ts;
    private String last_updated_by;

    @PrePersist
    public void onPrePersist() {
        this.setCreated_ts(LocalDateTime.now());
        this.setLast_updated_ts(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        this.setLast_updated_ts(LocalDateTime.now());
    }

}
