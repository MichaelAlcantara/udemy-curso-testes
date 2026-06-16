package com.MichaelAlcanatara.testesDeIntegracao.web;

import static com.MichaelAlcanatara.testesDeIntegracao.commom.PlanetConstants.PLANET;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.MichaelAlcanatara.testesDeIntegracao.domain.Planet;
import com.MichaelAlcanatara.testesDeIntegracao.domain.PlanetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PlanetService planetService;
	
	@Test
	public void createPlanet_WithValidData_ReturnsCreated() throws Exception {
		when(planetService.create(PLANET)).thenReturn(PLANET);
		
		mockMvc.perform(post("/planets").content(objectMapper.writeValueAsString(PLANET)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$").value(PLANET));
	}
	
	@Test
	public void createdPlanet_WithInvalidData_ReturnsBadRequest() throws JsonProcessingException, Exception {
		Planet empty = new Planet();
		Planet invalidPlanet = new Planet("","","");
		
		mockMvc.perform(post("/planets").content(objectMapper.writeValueAsString(empty)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
		
		mockMvc.perform(post("/planets").content(objectMapper.writeValueAsString(invalidPlanet)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
	}

}
