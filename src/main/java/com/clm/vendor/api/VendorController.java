package com.clm.vendor.api;

import com.clm.vendor.models.VendorDTO;
import com.clm.vendor.models.VendorResponseDTO;
import com.clm.vendor.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public ResponseEntity<VendorResponseDTO> createVendor(@RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorService.createVendor(vendorDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponseDTO> getVendor(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendor(id));
    }

    @GetMapping
    public ResponseEntity<List<VendorResponseDTO>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorResponseDTO> updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return ResponseEntity.ok(vendorService.updateVendor(id, vendorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}
