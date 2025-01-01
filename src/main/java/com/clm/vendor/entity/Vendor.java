package com.clm.vendor.entity;

import com.clm.vendor.utils.VendorMapConverter;
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

    @Column(columnDefinition = "jsonb")
    @Convert(converter = VendorMapConverter.class)
    Map<Long, List<Long>> categoryOptions;
}
