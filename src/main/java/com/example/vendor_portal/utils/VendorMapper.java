package com.example.vendor_portal.utils;

import com.example.vendor_portal.dtos.VendorDTO;
import com.example.vendor_portal.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);


    @Mapping(source = "regions", target = "regionIds", qualifiedByName = "mapRegions")
    @Mapping(source = "contractTypes", target = "contractTypeIds", qualifiedByName = "mapContractTypes")
    @Mapping(source = "organizationalFunctions", target = "organizationalFunctionIds", qualifiedByName="mapOrganizationalFunctions")
    @Mapping(source = "licensingModels", target = "licensingModelIds", qualifiedByName="mapLicensingModels")
    @Mapping(source = "capabilities", target = "capabilityIds", qualifiedByName="mapCapabilities")
    @Mapping(source = "integrations", target = "integrationIds", qualifiedByName = "mapIntegrations")
    VendorDTO toVendorDTO(Vendor vendor);

    @Named("mapRegions")
    public static List<Long> mapRegions(List<Region> regions) {
        return regions.stream()
                .map(Region::getId)
                .collect(Collectors.toList());
    }

    @Named("mapContractTypes")
    public static List<Long> mapContractTypes(List<ContractType> contractTypes) {
        return contractTypes.stream()
                .map(ContractType::getId)
                .collect(Collectors.toList());
    }

    @Named("mapOrganizationalFunctions")
    public static List<Long> mapFunctionsToIds(List<OrganizationalFunction> functions) {
        return functions.stream().map(OrganizationalFunction::getId).collect(Collectors.toList());
    }

    @Named("mapLicensingModels")
    public static List<Long> mapLicensingModelsToIds(List<LicensingModel> models) {
        return models.stream().map(LicensingModel::getId).collect(Collectors.toList());
    }

    @Named("mapCapabilities")
    public static List<Long> mapCapabilitiesToIds(List<Capability> capabilities) {
        return capabilities.stream().map(Capability::getId).collect(Collectors.toList());
    }

    @Named("mapIntegrations")
    public static List<Long> mapIntegrations(List<Integration> integrations) {
        return integrations.stream().map(Integration::getId).collect(Collectors.toList());
    }
}
