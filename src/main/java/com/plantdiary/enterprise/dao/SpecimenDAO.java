package com.plantdiary.enterprise.dao;

import com.plantdiary.enterprise.dto.Specimen;
import com.plantdiary.enterprise.exceptions.SpecimenNotFoundException;

import java.util.List;

public interface SpecimenDAO {
    Specimen getSpecimenById(long id);
    List<Specimen> fetchAll();
    public Specimen save(Specimen specimen) throws Exception;
    Specimen delete(long id);
}
