package com.plantdiary.enterprise;

import com.plantdiary.enterprise.dto.Specimen;
import com.plantdiary.enterprise.services.SpecimenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EnterpriseApplicationTests {
	@Autowired
	private SpecimenService specimenService;
	private Specimen specimen;
	@Test
	void contextLoads() {
	}
	@Test
	void getSpecimenEasternRedbudForID83() {
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

}
