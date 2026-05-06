package com.MichaelAlcanatara.testesDeIntegracao.commom;

import com.MichaelAlcanatara.testesDeIntegracao.domain.Planet;

public class PlanetConstants {
	public static final Planet PLANET = new Planet("name", "climate", "terrarin");
	public static final Planet INVALID_PLANET = new Planet("", "", "");
}
