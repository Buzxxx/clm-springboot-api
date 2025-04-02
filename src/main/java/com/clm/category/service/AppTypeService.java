package com.clm.category.service;

import com.clm.category.models.AppTypeDTO;

import java.util.List;

public interface AppTypeService {

    List<AppTypeDTO> getAllAppTypes();
    AppTypeDTO getAppTypeDetailsById(Long id);
}
