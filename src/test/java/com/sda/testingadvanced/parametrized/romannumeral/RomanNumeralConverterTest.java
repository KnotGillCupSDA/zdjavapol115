package com.sda.testingadvanced.parametrized.romannumeral;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class RomanNumeralConverterTest {

	@ParameterizedTest
	@EnumSource
	void testSimpleConversions(RomanNumeralConverter.ArabicToRoman arabicToRoman) {
		assertEquals(arabicToRoman.getRoman(), RomanNumeralConverter.romanFor(arabicToRoman.getArabic()));
	}

	@ParameterizedTest
	@EnumSource
	void shouldAlwaysBeAPositiveNumber(RomanNumeralConverter.ArabicToRoman arabicToRoman) {
		assertThat(arabicToRoman.getArabic()).isPositive();
	}

	@ParameterizedTest
	@CsvSource(value = { "CLXXXIX, 189", "MMXXII, 2022" })
	void shouldConvertNumbersRafal(String roman, int arabic) {
		assertThat(RomanNumeralConverter.romanFor(arabic)).isEqualTo(roman);
	}

	@ParameterizedTest
	@CsvSource(value = { "321, CCCXXI", "111, CXI" })
	void shouldBeTheSameBeforeConvertBartek(int number, String expected) {
		assertEquals(expected, RomanNumeralConverter.romanFor(number));

	}
}