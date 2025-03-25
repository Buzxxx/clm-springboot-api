package com.clm.category.api;

import com.clm.category.models.AppTypeDTO;
import com.clm.category.service.AppTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appType")
public class AppTypeController {

    private final AppTypeService appTypeService;

    public AppTypeController(AppTypeService appTypeService) {
        this.appTypeService = appTypeService;
    }

    @GetMapping
    public ResponseEntity<List<AppTypeDTO>> getAllAppTypes() {
        return ResponseEntity.ok(appTypeService.getAllAppTypes());
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<AppTypeDTO> getAppDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(appTypeService.getAppTypeDetailsById(id));
    }
}
