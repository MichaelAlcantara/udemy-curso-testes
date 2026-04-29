package com.MichaelAlcanatara.testes;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
	
	@Test
	public void testeSum() {
		Calculator calculator = new Calculator();
		assertThat(calculator.sum(1, 3)).isEqualTo(2);
	}
}
