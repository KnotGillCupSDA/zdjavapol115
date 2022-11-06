package com.sda.testingadvanced.parametrized;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DivisibilityCheckerTest {

	@ParameterizedTest
	@ValueSource(ints = {-9, 0, 9, 27})
	void shouldBeDivisibleBy3(int number) {
		assertTrue(DivisibilityChecker.isDivisibleBy3(number));
	}
}