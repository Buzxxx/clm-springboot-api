package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.SubType;
import com.clm.category.models.SubTypeDTO;

import java.util.Set;

public interface SubTypeService {

    Set<SubType> prepareSubTypes(AppType appType, Set<SubTypeDTO> subTypeDTOS, String username);
    Set<SubType> prepareSubTypesForUpdate(AppType appType, Set<SubTypeDTO> subTypeDTOS, String username);
}
