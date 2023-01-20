package com.plantdiary.enterprise;

import com.plantdiary.enterprise.dao.SpecimenDAO;
import com.plantdiary.enterprise.dto.Specimen;
import com.plantdiary.enterprise.services.SpecimenService;
import com.plantdiary.enterprise.services.SpecimenServiceStub;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
		whenSpecimen83AddedIsRedbud();
		whenSearchSpecimenWithID83();
		thenReturnOneEasternRedbudSpecimenForID83();
	}

	private void givenSpecimenEasternRedbudHasID83() {
	}

	private void whenSpecimen83AddedIsRedbud() {
		Specimen redbud = new Specimen();
		redbud.setId(83);
		redbud.setName("Eastern Redbud");
		Mockito.when(specimenDAO.getSpecimenById(83)).thenReturn(redbud);
		// Note - only returns the desired hardcoded specimen when specimenDAO's getSpecimenById() is called with id 83.
		// If this doesn't happen then our test will fail otherwise we're making sure that it passes.
	}

	private void whenSearchSpecimenWithID83() {
		specimenService = new SpecimenServiceStub(specimenDAO);
		specimen = specimenService.getSpecimenById(83);
		// specimenService.getSpecimenById(83) -> specimenDAO.getSpecimenById(83) -> return Specimen(83, "Eastern Redbud")
		// Note - our concern is only if specimenService's getSpecimenById works as expected or not. Which in this case depends on weather
		// it calls specimenDAO's getSpecimenById or not. Once it calls that then our test must pass. Hence, we make sure that mock of
		// specimenDAO returns eastern redbud specimen. (This is done in whenSpecimen83AddedIsRedbud)
	}

	private void thenReturnOneEasternRedbudSpecimenForID83() {
		String name = specimen.getName();
		assertEquals("Eastern Redbud", name);
	}

	@Test
	void saveSpecimen_saveAndReturnSpecimenWithLatitudeAndLongitude() throws Exception{
		givenSpecimenDataIsAvailable();
		whenCreateNewSpecimenAndSave();
		thenSaveAndReturnNewlyCreatedSpecimen();
	}

	private void givenSpecimenDataIsAvailable() throws Exception {
		// giving behaviour to a mocked class.
		Mockito.when(specimenDAO.save(specimen)).thenReturn(specimen);
		// Note - weather save() method of specimenDAO works properly or not is not our concern as we are testing SpecimenService and not SpecimenDAO

		// associating SpecimenService with SpecimenDAO (unapproved approach)
		specimenService = new SpecimenServiceStub(specimenDAO);
	}

	private void whenCreateNewSpecimenAndSave() {
		specimen.setLatitude(39.74F);
		specimen.setLongitude(-83.45F);
	}

	private void thenSaveAndReturnNewlyCreatedSpecimen() throws Exception {
		Specimen createdSpecimen = specimenService.save(specimen);
		// specimenService.save(specimen) -> return specimenDAO.save(specimen) -> return specimen
		// => Specimen createdSpecimen = specimen => both point to the same address in memory.
		assertEquals(specimen, createdSpecimen); // => specimen.equals(createdSpecimen)
	}

}
