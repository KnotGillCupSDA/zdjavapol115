package com.sda.testingadvanced.parametrized.conversion;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class MeasurementConverterTest {

	@ParameterizedTest
	@EnumSource
	void shouldCovertInDoubleBoundaries(ConversionType conversionType) {
		double convertedValue = new MeasurementConverter().convert(new Random().nextDouble(), conversionType);

		assertTrue(convertedValue < Double.MAX_VALUE);
		assertTrue(convertedValue > Double.MIN_VALUE);
	}

	@ParameterizedTest
	@EnumSource(value = ConversionType.class,
			names = { "METERS_TO_YARDS", "INCHES_TO_CENTIMETERS", "MILES_TO_KILOMETERS" })
	void shouldCovertToHigherValue(ConversionType conversionType) {
		double value = 10.345;
		double convertedValue = new MeasurementConverter().convert(value, conversionType);
		assertTrue(convertedValue > value);
	}

}