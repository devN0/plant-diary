package com.plantdiary.enterprise.controllers;

import com.plantdiary.enterprise.dto.ErrorResponseDto;
import com.plantdiary.enterprise.dto.Specimen;
import com.plantdiary.enterprise.exceptions.SpecimenNotFoundException;
import com.plantdiary.enterprise.services.SpecimenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlantDiaryController {
    private final SpecimenService specimenService;
    public PlantDiaryController(SpecimenService specimenService) {
        this.specimenService = specimenService;
    }
    @GetMapping("/specimen")
    public ResponseEntity<List<Specimen>> fetchAllSpecimens() {
        return ResponseEntity.ok(specimenService.fetchAll());
    }
    @GetMapping("/specimen/{id}")
    public ResponseEntity<Specimen> fetchSpecimenById(@PathVariable("id") long id) {
        return ResponseEntity.ok(specimenService.getSpecimenById(id));
    }

    @PostMapping("/specimen")
    public ResponseEntity<Specimen> createSpecimen(@RequestBody Specimen specimen) {
        Specimen createdSpecimen = null;
        try {
            createdSpecimen = specimenService.save(specimen);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(createdSpecimen);
    }

    @DeleteMapping("/specimen/{id}")
    public ResponseEntity<Specimen> deleteSpecimen(@PathVariable("id") long id) {
        Specimen deletedSpecimen = specimenService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedSpecimen);
    }

    @ExceptionHandler({
        SpecimenNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handleExceptions(Exception e) {
        if(e instanceof SpecimenNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(e.getMessage()));
    }
}
