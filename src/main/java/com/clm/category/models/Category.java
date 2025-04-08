package com.clm.category.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
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
}
