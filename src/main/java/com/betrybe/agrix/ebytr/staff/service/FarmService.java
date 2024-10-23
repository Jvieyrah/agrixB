package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropRequestDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FarmRequestDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.NotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.repository.FarmRepository;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {
  private FarmRepository farmRepository;
  private CropRepository cropRepository;

  private FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Farm service.
   * the farm repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository,
      CropRepository cropRepository,
      FertilizerRepository fertilizerRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Create farm.
   * the farm
   */
  public Farm createFarm(FarmRequestDto farmRequestDto) {
    Farm farm = new Farm();
    farm.setName(farmRequestDto.name());
    farm.setSize(farmRequestDto.size());
    return farmRepository.save(farm);
  }

  /**
   * Update Farm.
   */
  public Farm updateFarm(Long farmId, FarmRequestDto farmRequestDto) throws NotFoundException {
    Optional<Farm> targetFarm = farmRepository.findById(farmId);
    targetFarm.orElseThrow(() -> new NotFoundException("Fazenda não encontrada!"));
    targetFarm.get().setName(farmRequestDto.name());
    targetFarm.get().setSize(farmRequestDto.size());
    return farmRepository.save(targetFarm.get());
  }

  /**
   * Find all list.
   */
  public List<Farm> getFarms() {
    return farmRepository.findAll();
  }

  /**
   * Find by id farm.
   * the farm id
   * the farm not found exception
    */
  public Farm getFarm(Long farmId) throws NotFoundException {
    return farmRepository.findById(farmId)
        .orElseThrow(() -> new NotFoundException("Fazenda não encontrada!"));
  }

  /**
   * Create crop.
   */
  public Crop createCrop(Long farmId, CropRequestDto crop) throws NotFoundException {
    Optional<Farm> targetFarm = farmRepository.findById(farmId);
    targetFarm.orElseThrow(() -> new NotFoundException("Fazenda não encontrada!"));
    Crop newCrop = new Crop(crop.name(),
        crop.plantedArea(),
        crop.plantedDate(),
        crop.harvestDate());
    newCrop.setFarm(targetFarm.get());
    return cropRepository.save(newCrop);
  }

  /**
   * List crops.
   */
  public List<Crop> listAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Find cro by id.
   */
  public Crop findCropById(Long id) throws NotFoundException {
    Optional<Crop> targetCrop = cropRepository.findById(id);
    targetCrop.orElseThrow(() -> new NotFoundException("Plantação não encontrada!"));
    return targetCrop.get();
  }

  /**
   * List crops by date.
   */
  public List<Crop> listCropsByDate(String start, String end) {
    LocalDate startDate = LocalDate.parse(start).minusDays(1);
    LocalDate endDate = LocalDate.parse(end).plusDays(1);
    return cropRepository.findAllByHarvestDateBetween(startDate, endDate);
  }

  /**
   * Add fertilizer to crop.
   */
  public Crop addFertilizerToCrop(Long cropId, Long fertilizerId) {
    Crop crop = cropRepository.findById(cropId).orElseThrow(()
        -> new NotFoundException("Plantação não encontrada!"));
    Fertilizer fertilizer = fertilizerRepository
        .findById(fertilizerId).orElseThrow(()
            -> new NotFoundException("Fertilizante não encontrado!"));
    List<Fertilizer> fertilizers = crop.getFertilizers();
    fertilizers.add(fertilizer);
    crop.setFertilizers(fertilizers);
    cropRepository.save(crop);
    return crop;
  }

  /**
   * List fertilizers by crop.
   */
  public List<Fertilizer> listFertilizersByCrop(Long cropId) {
    Crop crop = cropRepository.findById(cropId).orElseThrow(()
        -> new NotFoundException("Plantação não encontrada!"));
    return crop.getFertilizers();
  }
}

