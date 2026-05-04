package com.MichaelAlcanatara.testesDeUnidade.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.MichaelAlcanatara.testesDeUnidade.commom.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PlanetService.class)
public class PlanetServiceTest {

	@Autowired
	private PlanetService planetService;

	// operacap_estado_retorno
	@Test
	public void createPlanet_WithValidData_ReturnsPlanet() {
		// sut = System Under Test
		Planet sut = planetService.create(PLANET);
		
		assertThat(sut).isEqualTo(PLANET);
	}
}
