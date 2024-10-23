package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.CropRequestDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FarmRequestDto;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.NotFoundException;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   * farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm dto.
   * farmCreationDto the farm creation dto
   */
  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody FarmRequestDto farmRequestDto) {
    Farm farm = farmService.createFarm(farmRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(farm);
  }

  /**
   * Gets all farms.
   */
  @GetMapping
  public ResponseEntity<List<Farm>> getFarms() {
    return ResponseEntity.ok(farmService.getFarms());
  }

  /**
   * Gets by id.
   * farmId the id
   * the farm not found exception
   */
  @GetMapping("{id}")
  public ResponseEntity<Farm> getFarm(@PathVariable Long id) throws NotFoundException {
    return ResponseEntity.ok(farmService.getFarm(id));
  }

  /**
   * Create new crop.
   * Based on farmId.
   * the farm not found exception
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId,
      @RequestBody CropRequestDto crop) throws NotFoundException {
    CropDto createdCrop = CropDto.fromEntity(farmService.createCrop(farmId, crop));
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCrop);
  }

  /**
   * Get crops.
   * Based on farmId.
   * the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> listAllCrops(
      @PathVariable Long farmId) throws NotFoundException {
    Farm farm = farmService.getFarm(farmId);
    List<CropDto>  crops = farm.getCrops().stream()
        .map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(crops);
  }
}
