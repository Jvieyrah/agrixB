package com.betrybe.agrix.ebytr.staff.entity;

import com.betrybe.agrix.ebytr.staff.controller.dto.FarmRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Farm.
 */
@Entity
@Table(name = "farms")
public class Farm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private double size;

  @OneToMany(mappedBy = "farm")
  @JsonIgnore
  private List<Crop> crops = new ArrayList<>();

  /**
   * Instantiates a new Farm.
   */
  public Farm() {
  }

  /**
   * Instantiates a new Farm.
   */
  public Farm(FarmRequestDto farmRequestDto) {
    name = farmRequestDto.name();
    size = farmRequestDto.size();
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
   * Gets name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets size.
   */
  public double getSize() {
    return size;
  }

  /**
   * Sets size.
   */
  public void setSize(Double size) {
    this.size = size;
  }

  /**
   * Gets Crops.
   */
  public List<Crop> getCrops() {
    return crops;
  }

  /**
   * Sets Crops.
   */
  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}
