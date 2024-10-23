package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;

/**
 * The record FertilizerDto.
 */
public record FertilizerReturnDto(
    Long id,
    String name,
    String brand,
    String composition
) {

  /**
   * The method static FertilizerReturnDto.
   */
  public static FertilizerReturnDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerReturnDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}