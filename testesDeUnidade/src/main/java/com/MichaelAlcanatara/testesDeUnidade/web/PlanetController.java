package com.MichaelAlcanatara.testesDeUnidade.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MichaelAlcanatara.testesDeUnidade.domain.Planet;
import com.MichaelAlcanatara.testesDeUnidade.domain.PlanetService;

@RestController
@RequestMapping("/planets")
public class PlanetController {

	@Autowired
	private PlanetService planetService;

	@PostMapping
	public ResponseEntity<Planet> create(@RequestBody Planet plant) {
		Planet planedCreated = planetService.create(plant);
		return ResponseEntity.status(HttpStatus.CREATED).body(planedCreated);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Planet> get(@PathVariable("id") Long id) {
		return planetService.get(id).map(planet -> ResponseEntity.ok(planet))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

}
