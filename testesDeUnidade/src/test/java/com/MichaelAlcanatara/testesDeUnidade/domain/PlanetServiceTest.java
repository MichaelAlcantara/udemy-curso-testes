package com.MichaelAlcanatara.testesDeUnidade.domain;

import static com.MichaelAlcanatara.testesDeUnidade.commom.PlanetConstants.INVALID_PLANET;
import static com.MichaelAlcanatara.testesDeUnidade.commom.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//@SpringBootTest(classes = PlanetService.class)
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

	//@Autowired
	@InjectMocks
	private PlanetService planetService;
	
	//@MockBean
	@Mock
	private PlanetRepository planetRepository;

	// operacao_estado_retorno
	@Test
	public void createPlanet_WithValidData_ReturnsPlanet() {
		
		//AAA
		// A -> Arrange
		when(planetRepository.save(PLANET)).thenReturn(PLANET);
		
		// A -> Act
		// sut = System Under Test
		Planet sut = planetService.create(PLANET);
		
		// A -> Assert
		assertThat(sut).isEqualTo(PLANET);
	}
	
	@Test
	public void createPlanet_WithInvalidData_ThrowsException() {
		
		when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);
		
		assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
	}
}
