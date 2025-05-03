package com.saandeep.govrn.controller;

import com.saandeep.govrn.dto.RegionDTO;
import com.saandeep.govrn.model.Region;
import com.saandeep.govrn.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {
    @Autowired
    RegionService regionService;

    @GetMapping("/")
    public ResponseEntity<List<Region>> getRegions() {
        List<Region> regions = regionService.getAllRegions();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegion(@PathVariable Long id) {
        Region region = regionService.getRegion(id);
        return new ResponseEntity<>(region, region != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping("/")
    public ResponseEntity<Region> createRegion(@Valid @RequestBody RegionDTO regionDTO) {
        Region region = regionService.createRegion(regionDTO);
        return new ResponseEntity<>(region, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@RequestBody Region region, @PathVariable Long id) {
        Region updatedRegion = regionService.updateRegion(id, region);
        return new ResponseEntity<>(updatedRegion, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Region> deleteRegion(@PathVariable Long id) {
        Region region = regionService.deleteRegion(id, true);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }
}
