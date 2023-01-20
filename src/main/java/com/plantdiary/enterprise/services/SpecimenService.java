package com.plantdiary.enterprise.services;

import com.plantdiary.enterprise.dto.Specimen;
import com.plantdiary.enterprise.exceptions.SpecimenNotFoundException;

import java.util.List;

public interface SpecimenService {
    Specimen getSpecimenById(long id);

    Specimen save(Specimen specimen) throws Exception;

    List<Specimen> fetchAll();

    Specimen delete(long id);
}
