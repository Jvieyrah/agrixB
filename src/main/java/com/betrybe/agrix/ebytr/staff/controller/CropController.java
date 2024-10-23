package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerReturnDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.NotFoundException;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final FarmService farmService;

  /**
   * The type Crop constructor.
   */
  @Autowired
  public CropController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * get all Crops.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> listAllCrops() {
    List<Crop> crops = farmService.listAllCrops();
    List<CropDto> editedCrops = crops.stream().map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.ok().body(editedCrops);
  }

  /**
   * get Crops by date.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> listCropsByDate(
      @RequestParam String start, @RequestParam String end) {
    List<Crop> crops = farmService.listCropsByDate(start, end);
    List<CropDto> editedCrops = crops.stream().map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.ok().body(editedCrops);
  }

  /**
   * get Crop by id.
   */
  @GetMapping("/{cropId}")
  public ResponseEntity<CropDto> findCropById(@PathVariable Long cropId) throws NotFoundException {
    CropDto crop = CropDto.fromEntity(farmService.findCropById(cropId));
    return ResponseEntity.ok().body(crop);
  }

  /**
   * Create new Crop.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<?> addFertilizerToCrop(
      @PathVariable Long cropId, @PathVariable Long fertilizerId)
      throws NotFoundException {
    farmService.addFertilizerToCrop(cropId, fertilizerId);
    return ResponseEntity.created(null)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * List fertilizers by crop...
   */
  @GetMapping("/{cropId}/fertilizers")

  public ResponseEntity<?> listFertilizersByCrop(
      @PathVariable Long cropId) throws NotFoundException {
    List<Fertilizer> fertilizers = farmService
        .listFertilizersByCrop(cropId);
    return ResponseEntity.ok().body(fertilizers
        .stream().map(FertilizerReturnDto::fromEntity).toList());
  }
}