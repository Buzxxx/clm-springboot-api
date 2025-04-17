package com.clm.category.api;

import com.clm.auth.service.AuthService;
import com.clm.category.models.AppTypeDTO;
import com.clm.category.service.AppTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appTypes")
public class AppTypeController {

    private final AppTypeService appTypeService;
    private final AuthService authService;
    public AppTypeController(AppTypeService appTypeService, AuthService authService) {
        this.appTypeService = appTypeService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<AppTypeDTO>> getAllAppTypes() {
        return ResponseEntity.ok(appTypeService.getAllAppTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppTypeDTO> getAppDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(appTypeService.getAppTypeDetailsById(id));
    }

    @PostMapping
    public ResponseEntity<?> createAppType(@RequestBody AppTypeDTO appTypeDTO, HttpServletRequest request) {
        String username = authService.retrieveUsernameFromHeader(request);
        appTypeService.createAppType(appTypeDTO, username);
        return ResponseEntity.status(HttpStatus.CREATED).body("App and Downstream Objects Created Successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateAppType(@RequestBody AppTypeDTO appTypeDTO, HttpServletRequest request) {
        String username = authService.retrieveUsernameFromHeader(request);
        appTypeService.updateAppType(appTypeDTO, username);
        return ResponseEntity.status(HttpStatus.OK).body("App and downstream objects updated successfully");
    }
}
