package com.clm.category.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubType {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "app_type_id")
    private AppType appType;

    // One SubType has many Categories
    @OneToMany(mappedBy = "subType", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<Category> categories = new HashSet<>();

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
