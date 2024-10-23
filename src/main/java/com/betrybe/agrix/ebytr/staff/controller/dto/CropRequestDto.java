package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;

/**
 * Crop request dto.
 */
public record CropRequestDto(String name,
                             double plantedArea,
                             LocalDate plantedDate,
                             LocalDate harvestDate) {
  public Crop toEntity() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}
