package com.MichaelAlcanatara.testesDeIntegracao;

import static com.MichaelAlcanatara.testesDeIntegracao.commom.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.MichaelAlcanatara.testesDeIntegracao.domain.Planet;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/remove_planet.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class PlanetIT {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void createPlanet_ReturnCreated() {
		ResponseEntity<Planet> sut = restTemplate.postForEntity("/planets", PLANET, Planet.class);
		
		assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(sut.getBody().getId()).isNotNull();
		assertThat(sut.getBody().getName()).isEqualTo(PLANET.getName());
		assertThat(sut.getBody().getClimate()).isEqualTo(PLANET.getClimate());
		assertThat(sut.getBody().getTerrain()).isEqualTo(PLANET.getTerrain());
	}
}
