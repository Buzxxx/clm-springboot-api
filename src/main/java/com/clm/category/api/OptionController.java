package com.clm.category.api;

import com.clm.category.models.OptionDTO;
import com.clm.category.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/options")
public class OptionController {
    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public ResponseEntity<List<OptionDTO>> getAllOptions() {
        return ResponseEntity.ok(optionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionDTO> getOption(@PathVariable Long id) {
        return ResponseEntity.ok(optionService.findById(id));
    }

//    @PostMapping
//    public ResponseEntity<OptionDTO> createOption(@RequestBody OptionDTO optionDTO) {
//        OptionDTO created = optionService.create(optionDTO);
//        return ResponseEntity
//                .created(URI.create("/api/v1/options" + created.getId()))
//                .body(created);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionDTO> updateOption(@PathVariable Long id, @RequestBody OptionDTO optionDTO) {
        return ResponseEntity.ok(optionService.update(id, optionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        optionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

