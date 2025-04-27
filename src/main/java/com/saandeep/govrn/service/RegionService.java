package com.saandeep.govrn.service;

import com.saandeep.govrn.model.Region;
import com.saandeep.govrn.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getRegion(Long id) {
        return regionRepository.findById(id).orElse(null);
    }
}
