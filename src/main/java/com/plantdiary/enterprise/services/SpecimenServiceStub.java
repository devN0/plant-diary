package com.plantdiary.enterprise.services;

import com.plantdiary.enterprise.dto.Specimen;
import org.springframework.stereotype.Service;

@Service
public class SpecimenServiceStub implements SpecimenService {
    Specimen specimen = new Specimen(83, "Eastern Redbud", (float)39.74, (float)42.53);
    @Override
    public Specimen getSpecimenById(int id) {
        return specimen;
    }
}
