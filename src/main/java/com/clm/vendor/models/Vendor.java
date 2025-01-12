package com.clm.vendor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Vendor {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = VendorMapConverter.class)
    Map<Long, List<Long>> categoryOptions;
}
