package com.betrybe.agrix.ebytr.staff.repository;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface FertilizerRepository.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}
