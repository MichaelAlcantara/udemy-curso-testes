package com.MichaelAlcanatara.testesDeUnidade.domain;

import static com.MichaelAlcanatara.testesDeUnidade.commom.PlanetConstants.INVALID_PLANET;
import static com.MichaelAlcanatara.testesDeUnidade.commom.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//@SpringBootTest(classes = PlanetService.class)
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

	// @Autowired
	@InjectMocks
	private PlanetService planetService;

	// @MockBean
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

	@Test
	public void getPlanet_ByExistingId_ReturnsPlanet() {
		when(planetRepository.findById(1L)).thenReturn(Optional.empty());

	    Optional<Planet> sut = planetService.get(1L);

	    assertThat(sut).isEmpty();
	}

	@Test
	public void getPlanet_ByUnexistingId_ReturnsEmpty() {
		when(planetRepository.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));

	    Optional<Planet> sut = planetService.getByName(PLANET.getName());

	    assertThat(sut).isNotEmpty();
	    assertThat(sut.get()).isEqualTo(PLANET);
	}
}
