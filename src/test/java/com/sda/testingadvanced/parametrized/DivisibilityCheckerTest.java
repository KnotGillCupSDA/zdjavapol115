package com.sda.testingadvanced.parametrized;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DivisibilityCheckerTest {

	@ParameterizedTest
	@ValueSource(ints = {-9, 0, 9, 27})
	void shouldBeDivisibleBy3(int number) {
		assertTrue(DivisibilityChecker.isDivisibleBy3(number));
	}

	@ParameterizedTest
	@ValueSource(ints = {2, -8, 4, 61})
	void shouldNotBeDivisibleBy3(int number) {
		//when
		boolean notDivisibleBy3 = DivisibilityChecker.isDivisibleBy3(number);

		//then
		assertFalse(notDivisibleBy3);
	}
}