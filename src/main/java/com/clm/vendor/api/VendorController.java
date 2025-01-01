package com.clm.vendor.api;

import com.clm.dtos.VendorDTO;
import com.clm.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Value("${pagination.page-size}")
    private int pageSize;

    private final VendorService vendorService;


    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<List<VendorDTO>> getVendors(@RequestParam(defaultValue = "0") int page) {

        PageRequest pageable = PageRequest.of(page, pageSize);

        return ResponseEntity.ok(vendorService.getAllVendors(pageable));
    }


}
