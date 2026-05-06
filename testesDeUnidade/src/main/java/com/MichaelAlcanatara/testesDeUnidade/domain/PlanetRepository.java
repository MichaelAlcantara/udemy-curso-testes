package com.MichaelAlcanatara.testesDeUnidade.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends CrudRepository<Planet, Long> {

	Optional<Planet> findByName(String name);

	<S extends Planet> List<S> findAll(Example<S> example);

}
