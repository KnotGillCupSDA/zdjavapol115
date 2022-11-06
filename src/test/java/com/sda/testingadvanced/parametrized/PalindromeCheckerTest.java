package com.sda.testingadvanced.parametrized;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PalindromeCheckerTest {

	@ParameterizedTest
	@CsvSource(value = { "kok, true", "sok, false", "kajak, true", "KOK, true", "koK, true", ",false", "'', true" })
	void testedPalindrome(String text, boolean expected) {
		assertEquals(expected, PalindromeChecker.isPalindrome(text));
		assertEquals(expected, PalindromeChecker.isPalindromeDavid(text));
		assertEquals(expected, PalindromeChecker.isPalindromeIwo(text));
		assertEquals(expected, PalindromeChecker.isPalindromeStringBuilder(text));
	}
}