package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.AppTypeDTO;
import com.clm.category.models.SubType;
import com.clm.category.models.SubTypeDTO;

import java.util.List;
import java.util.Set;

public interface AppTypeService {

    List<AppTypeDTO> getAllAppTypes();
    AppTypeDTO getAppTypeDetailsById(Long id);
    void createAppType(AppTypeDTO appTypeDTO, String username);
//    Set<SubType> prepareSubTypes(AppType appType, Set<SubTypeDTO> subTypeDTOS, String username);
//    Set<SubType> prepareSubTypesForUpdate(AppType appType, Set<SubTypeDTO> subTypeDTOS, String username);
    void updateAppType(AppTypeDTO appTypeDTO, String username);
}
