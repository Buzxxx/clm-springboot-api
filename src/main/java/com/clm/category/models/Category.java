package com.clm.category.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
public class Category {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;
    private String image;



    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private Set<Option> options = new HashSet<>();

    public void addOption(Option option) {
        options.add(option);
        option.setCategory(this);
    }

    public void removeOption(Option option) {
        options.remove(option);
        option.setCategory(null);
    }

    @ManyToOne
    @JoinColumn(name = "app_type_id")
    private AppType appType;

    @ManyToOne
    @JoinColumn(name = "sub_type_id")
    private SubType subType;

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
