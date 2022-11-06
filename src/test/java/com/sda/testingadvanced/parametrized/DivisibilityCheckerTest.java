package com.sda.testingadvanced.parametrized;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class DivisibilityCheckerTest {

	@ParameterizedTest
	@ValueSource(ints = { -9, 0, 9, 27 })
	void shouldBeDivisibleBy3(Integer number) {
		assertTrue(DivisibilityChecker.isDivisibleBy3(number));
	}

	@ParameterizedTest
	@NullSource
	@ValueSource(ints = { 2, -8, 4, 61 })
	void shouldNotBeDivisibleBy3(Integer number) {
		//when
		boolean notDivisibleBy3 = DivisibilityChecker.isDivisibleBy3(number);

		//then
		assertFalse(notDivisibleBy3);
	}

	@ParameterizedTest
	@CsvSource(value = { "-1, false", "0, true", "1, false", "3, true", "9, true" })
	void testDivisibilityBy3(int number, boolean expected) {
		assertEquals(expected, DivisibilityChecker.isDivisibleBy3(number));
	}

}