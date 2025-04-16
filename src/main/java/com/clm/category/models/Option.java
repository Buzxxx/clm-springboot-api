package com.clm.category.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "options")
@Builder
public class Option {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

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
