package com.betrybe.agrix.ebytr.staff.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Crop.
 */
@Entity
@Table(name = "crops")
public class Crop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private double plantedArea;

  private LocalDate plantedDate;

  private LocalDate harvestDate;


  @ManyToOne
  @JoinColumn(name = "farmId")
  private Farm farm;

  /**
   * Instantiates a new Crop.
   */
  public Crop() {
  }

  /**
   * Instantiates a new Crop.
   */
  public Crop(String name, double plantedArea, LocalDate plantedDate, LocalDate harvestDate) {
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
  }

  /**
   * Gets id.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets Name.
   */
  public String getName() {
    return name;
  }

  /**
   * sets name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets plantedArea.
   */
  public double getPlantedArea() {
    return plantedArea;
  }

  /**
   * sets plantedArea.
   */
  public void setPlantedArea(double plantedArea) {
    this.plantedArea = plantedArea;
  }

  /**
   * Gets farm.
   */
  public Farm getFarm() {
    return farm;
  }

  @ManyToMany
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "crop_id"),
      inverseJoinColumns = @JoinColumn(name = "fertilizer_id")
  )
  private List<Fertilizer> fertilizers;


  /**
   * Gets farm.
   */
  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return this.fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }

}
