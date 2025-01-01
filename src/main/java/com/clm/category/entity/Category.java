package com.clm.category.entity;

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

    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Option> options = new HashSet<>();

    public void addOption(Option option) {
        options.add(option);
        option.setCategory(this);
    }

    public void removeOption(Option option) {
        options.remove(option);
        option.setCategory(null);
    }
}
