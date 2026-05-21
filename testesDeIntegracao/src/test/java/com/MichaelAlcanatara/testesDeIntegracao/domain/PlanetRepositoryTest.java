package com.MichaelAlcanatara.testesDeIntegracao.domain;

import static com.MichaelAlcanatara.testesDeIntegracao.commom.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest //Cria um banco de dados em mémoria, só para testa
public class PlanetRepositoryTest {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private TestEntityManager testEntityManager; //Usado para fazer a busca sem ser no repositorio
	
	@Test
	public void createPlanet_WithValidData_ReturnsPlanet() {
		Planet planet = planetRepository.save(PLANET);
		
		Planet sut = testEntityManager.find(Planet.class, planet.getId());
		
		assertThat(sut).isNotNull();
		assertThat(sut.getName()).isEqualTo(PLANET.getName());
		assertThat(sut.getClimate()).isEqualTo(PLANET.getClimate());
		assertThat(sut.getTerrain()).isEqualTo(PLANET.getTerrain());
	}
	
	@Test
	public void createPlanet_WithInvalidData_ThrowsExceptions() {
		Planet empty = new Planet();
		Planet invalidPlanet = new Planet("","","");
		
		assertThatThrownBy( () -> planetRepository.save(empty)).isInstanceOf(RuntimeException.class);
		assertThatThrownBy( () -> planetRepository.save(invalidPlanet)).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void createPlanet_WithExistingName_ThrowsException() {
		
		Planet planet = testEntityManager.persistFlushFind(PLANET);
		testEntityManager.detach(planet);
		planet.setId(null);
		
		assertThatThrownBy(() ->  planetRepository.save(PLANET)).isInstanceOf(RuntimeException.class);
	}

}
