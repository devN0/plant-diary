package com.plantdiary.enterprise;

import com.plantdiary.enterprise.dao.SpecimenDAO;
import com.plantdiary.enterprise.dto.Specimen;
import com.plantdiary.enterprise.services.SpecimenService;
import com.plantdiary.enterprise.services.SpecimenServiceStub;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EnterpriseApplicationTests {
	private SpecimenService specimenService;
	private Specimen specimen = new Specimen();
	@MockBean
	private SpecimenDAO specimenDAO; // mockito will create its own implementation of SpecimenDAO and fill it with whatever we define in Mockito.when()
	@Test
	void contextLoads() {
	}
	@Test
	void getSpecimenById_getSpecimenEasternRedbudForID83() {
		givenSpecimenEasternRedbudHasID83();
		whenSearchSpecimenWithID83();
		thenReturnOneEasternRedbudSpecimenForID83();
	}

	private void givenSpecimenEasternRedbudHasID83() {
	}

	private void whenSearchSpecimenWithID83() {
		specimen = specimenService.getSpecimenById(83);
	}

	private void thenReturnOneEasternRedbudSpecimenForID83() {
		String name = specimen.getName();
		assertEquals("Eastern Redbud", name);
	}

	@Test
	void saveSpecimen_saveAndReturnSpecimenWithLatitudeAndLongitude() {
		givenSpecimenDataIsAvailable();
		whenCreateNewSpecimenAndSave();
		thenSaveAndReturnNewlyCreatedSpecimen();
	}

	private void givenSpecimenDataIsAvailable() {
		// giving behaviour to a mocked class.
		Mockito.when(specimenDAO.save(specimen)).thenReturn(specimen);
		// associating SpecimenService with SpecimenDAO (unapproved approach)
		specimenService = new SpecimenServiceStub(specimenDAO);
	}

	private void whenCreateNewSpecimenAndSave() {
		specimen.setLatitude(39.74F);
		specimen.setLongitude(-83.45F);
	}

	private void thenSaveAndReturnNewlyCreatedSpecimen() {
		Specimen createdSpecimen = specimenService.save(specimen);
		// specimenService.save(specimen) -> return specimenDAO.save(specimen) -> return specimen
		// => Specimen createdSpecimen = specimen => both point to the same address in memory.
		assertEquals(specimen, createdSpecimen); // => specimen.equals(createdSpecimen)
	}

}
