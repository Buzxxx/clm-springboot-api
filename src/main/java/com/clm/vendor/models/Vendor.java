package com.clm.vendor.models;

import com.clm.category.models.AppType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

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
    private LocalDate supplier_commencement_date;
    private LocalDate business_started_date;

    @ManyToOne
    @JoinColumn(name = "app_type_id")
    private AppType appType;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Map<Long, List<Long>> categoryOptions;
    private LocalDateTime created_ts;
    private String created_by;
    private LocalDateTime last_updated_ts;
    private String last_updated_by;
}
