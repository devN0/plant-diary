package com.plantdiary.enterprise.services;

import com.plantdiary.enterprise.dao.SpecimenDAO;
import com.plantdiary.enterprise.dto.Specimen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Service
public class SpecimenServiceStub implements SpecimenService {
    private SpecimenDAO specimenDAO;

    public SpecimenServiceStub() {}
    public SpecimenServiceStub(SpecimenDAO specimenDAO) {
        this.specimenDAO = specimenDAO;
    }

    Specimen specimen = new Specimen(83L, "Eastern Redbud", 39.74F, 42.53F);
    @Override
    public Specimen getSpecimenById(int id) {
        return specimen;
    }

    @Override
    public Specimen save(Specimen specimen) {
        return specimenDAO.save(specimen);
    }
}
