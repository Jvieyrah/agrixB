package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import java.time.LocalDate;

/**
 * Crops.
 */
public record CropDto(Long id, String name, double plantedArea, Long farmId,
                      LocalDate plantedDate, LocalDate harvestDate) {

  /** CropDto fromEntity.
  * Convert Crop entity to CropDto.
   */
  public static CropDto fromEntity(Crop crop) {
    Farm farm = crop.getFarm();
    return new CropDto(crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        farm.getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate());
  }
}