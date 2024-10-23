package com.betrybe.agrix.ebytr.staff.controller;


import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerReturnDto;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
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
 * The Controller Fertilizer.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * The method create.
   */
  @PostMapping
  public ResponseEntity<?> create(@RequestBody FertilizerCreationDto fertilizerCreationDto) {
    System.out.println("chamou " + fertilizerCreationDto);
    FertilizerReturnDto fertilizerReturnDto = FertilizerReturnDto.fromEntity(
        fertilizerService.create(fertilizerCreationDto.toEntity())
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerReturnDto);
  }

  /**
   * The method getAll.
   */
  @GetMapping
  public ResponseEntity<?> listAllFertilize() {
    List<Fertilizer> fertilizers = fertilizerService.findByAll();
    return ResponseEntity.status(HttpStatus.OK).body(
        fertilizers.stream().map(FertilizerReturnDto::fromEntity).toList()
    );
  }

  /**
   * The method getFertilizerById.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = fertilizerService.findById(id);
    if (fertilizer == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(FertilizerReturnDto.fromEntity(fertilizer));
  }
}