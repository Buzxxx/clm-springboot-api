package com.clm.category.service;

import com.clm.category.models.AppTypeDTO;

import java.util.List;

public interface AppTypeService {

    public List<AppTypeDTO> getAllAppTypes();
    public AppTypeDTO getAppTypeDetailsById(Long id);
}
