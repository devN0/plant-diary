package com.plantdiary.enterprise.services;

import com.plantdiary.enterprise.dao.SpecimenDAO;
import com.plantdiary.enterprise.dto.Specimen;
import com.plantdiary.enterprise.exceptions.SpecimenNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class SpecimenServiceStub implements SpecimenService {
    private SpecimenDAO specimenDAO;

    public SpecimenServiceStub() {}
    @Autowired
    public SpecimenServiceStub(SpecimenDAO specimenDAO) {
        this.specimenDAO = specimenDAO;
    }

    @Override
    public Specimen getSpecimenById(long id) {
        Specimen foundSpecimen = specimenDAO.getSpecimenById(id);
        if(foundSpecimen == null){
            throw new SpecimenNotFoundException(id);
        }
        return foundSpecimen;
    }

    @Override
    public List<Specimen> fetchAll() {
        return specimenDAO.fetchAll();
    }

    @Override
    public Specimen save(Specimen specimen) throws Exception {
        return specimenDAO.save(specimen);
    }

    @Override
    public Specimen delete(long id) {
        Specimen deletedSpecimen = specimenDAO.delete(id);
        if(deletedSpecimen == null){
            throw new SpecimenNotFoundException(id);
        }
        return deletedSpecimen;
    }
}
