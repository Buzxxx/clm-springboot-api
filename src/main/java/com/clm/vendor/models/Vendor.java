package com.clm.vendor.models;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String software_name;
    private String description;
    private String website;
    private String logo;
    private Boolean is_verified;
    private LocalDateTime supplier_commencement_date;
    private LocalDateTime business_started_date;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Map<Long, List<Long>> categoryOptions;
    private LocalDateTime created_ts;
    private String created_by;
    private LocalDateTime last_updated_ts;
    private String last_updated_by;
}
