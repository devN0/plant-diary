package com.plantdiary.enterprise.dao;

import com.plantdiary.enterprise.dto.Specimen;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SpecimenDAOStub implements SpecimenDAO {

    Map<Long, Specimen> allSpecimens = new HashMap();

    @Override
    public Specimen getSpecimenById(long id) {
        return allSpecimens.get(id);
    }
    @Override
    public List<Specimen> fetchAll() {
        return new ArrayList<>(allSpecimens.values());
    }
    @Override
    public Specimen save(Specimen specimen) throws Exception {
        allSpecimens.put(specimen.getId(), specimen);
        return allSpecimens.get(specimen.getId());
    }
    @Override
    public Specimen delete(long id) {
        return allSpecimens.remove(id);
    }
}
