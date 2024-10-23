package com.betrybe.agrix.ebytr.staff.controller.dto;


import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import java.util.List;

/**
 * The record FertilizerDto.
 */
public record FertilizerDto(
    Long id,
    String name,
    String brand,
    String composition,
    List<CropDto> crops
) {

  /**
   * The method static FertilizerDto.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition(),
        fertilizer.getCrops().stream().map(CropDto::fromEntity).toList()
    );
  }
}
