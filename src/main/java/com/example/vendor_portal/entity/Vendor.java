package com.example.vendor_portal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Vendor {

    @Id
    @GeneratedValue
    private Long id;

    private String vendorName;
    private String logo;
    private String email;
    private String website;
    private int estYr;

    private String description;

    private String vendorServices;
    private int vendorMatchPercentage;
    private boolean isVerified;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "vendor_regions",
    joinColumns = @JoinColumn(name="vendor_id"),
    inverseJoinColumns = @JoinColumn(name = "region_id"))
    private List<Region> regions;

    @JoinTable(name = "vendor_capabilities",
            joinColumns = @JoinColumn(name="vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "capability_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Capability> capabilities;

    @JoinTable(name = "vendor_organization_functions",
            joinColumns = @JoinColumn(name="vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "organizational_function_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrganizationalFunction> organizationalFunctions;

    @JoinTable(name = "vendor_contract_types",
            joinColumns = @JoinColumn(name="vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "contract_type_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ContractType> contractTypes;

    @JoinTable(name = "vendor_licensing_models",
            joinColumns = @JoinColumn(name="vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "licensing_model_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<LicensingModel> licensingModels;

    @JoinTable(name = "vendor_integrations",
            joinColumns = @JoinColumn(name="vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "integration_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Integration> integrations;

}
