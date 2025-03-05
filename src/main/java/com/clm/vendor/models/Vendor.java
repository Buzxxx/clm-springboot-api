package com.clm.vendor.models;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

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
    private String logo;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
//    @Convert(converter = VendorMapConverter.class)
    Map<Long, List<Long>> categoryOptions;
}
