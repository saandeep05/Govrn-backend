package com.saandeep.govrn.service;

import com.saandeep.govrn.dto.RegionDTO;
import com.saandeep.govrn.model.Region;
import com.saandeep.govrn.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getRegion(Long id) {
        return regionRepository.findById(id).orElse(null);
    }

    public Region createRegion(RegionDTO regionDTO) {
        Region region = new Region(
                null,
                regionDTO.getBase(),
                regionDTO.getDistrict(),
                regionDTO.getState(),
                "INDIA",
                regionDTO.getPinCode(),
                null,
                null
        );
        return regionRepository.save(region);
    }

    public Region updateRegion(Long regionId, Region region) {
        Region fetchedRegion = getRegion(regionId);
        if(fetchedRegion == null){
            return null;
        }

        region.setId(regionId);
        region.setUpdatedAt(LocalDateTime.now());
        return regionRepository.save(region);
    }

    public Region deleteRegion(Long id, boolean isSoft) {
        Region region = getRegion(id);
        if(region == null) return null;

        if(!isSoft) {
            regionRepository.deleteById(id);
            return null;
        }

        region.setDeletedAt(LocalDateTime.now());
        return regionRepository.save(region);
    }
}
