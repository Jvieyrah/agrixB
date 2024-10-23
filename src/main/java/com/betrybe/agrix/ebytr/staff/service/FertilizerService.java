package com.betrybe.agrix.ebytr.staff.service;


import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling fertilizers business logic.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Creates a new Fertilizer.
   */
  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Get all fertilizers.
   */
  public List<Fertilizer> findByAll() {
    return fertilizerRepository.findAll();
  }

  /**
   * Get a fertilizer by id.
   */

  public Fertilizer findById(Long id) {
    return fertilizerRepository.findById(id).orElse(null);
  }
}
