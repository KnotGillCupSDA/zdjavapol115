package com.sda.testingadvanced.parametrized;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DivisibilityCheckerTest {

	@Test
	void shouldBeDivisibleBy3() {
		//given
		int number = 9;

		//when
		boolean divisibleBy3 = DivisibilityChecker.isDivisibleBy3(number);

		//then
		assertTrue(divisibleBy3);
	}

	@Test
	void shouldNotBeDivisibleBy3() {
		//given
		int number = 10;

		//when
		boolean divisibleBy3 = DivisibilityChecker.isDivisibleBy3(number);

		//then
		assertFalse(divisibleBy3);
	}
}