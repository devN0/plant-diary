package com.plantdiary.enterprise.controllers;

import com.plantdiary.enterprise.dto.ErrorResponseDto;
import com.plantdiary.enterprise.dto.Specimen;
import com.plantdiary.enterprise.exceptions.InvalidCredentialsException;
import com.plantdiary.enterprise.exceptions.SpecimenNotFoundException;
import com.plantdiary.enterprise.services.SpecimenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlantDiaryController {
    private final SpecimenService specimenService;
    public PlantDiaryController(SpecimenService specimenService) {
        this.specimenService = specimenService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        Specimen specimen = new Specimen();
        model.addAttribute(specimen);
        return "start";
    }

    @RequestMapping("/sustainability")
    public String sustainability() {
        return "sustainability";
    }

    @RequestMapping("/saveSpecimen")
    public String saveSpecimen(Specimen specimen) {
        try {
            specimenService.save(specimen);
        } catch(Exception e) {
            e.printStackTrace();
            return "start";
        }
        return "start";
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

    @GetMapping("/plants")
    public ResponseEntity searchPlants(@RequestParam(value="searchTerm", required=false) String searchTerm) {
        System.out.println(searchTerm);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler({
        SpecimenNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handleExceptions(Exception e) {
        if(e instanceof SpecimenNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(e.getMessage()));
        } else if(e instanceof InvalidCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(e.getMessage()));
    }

}
