package com.sda.testingadvanced.parametrized.conversion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

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

	@ParameterizedTest(name = "{index}: {3}")
	@MethodSource("testData")
	void someOperationsShouldBeReversible(
			ConversionType conversionType1,
			ConversionType conversionType2,
			double originalValue,
			String testFailMessage) {
		MeasurementConverter converter = new MeasurementConverter();
		double firstConversionResult = converter.convert(originalValue, conversionType1);
		double secondConversionResult = converter.convert(firstConversionResult, conversionType2);
		assertEquals(originalValue, secondConversionResult, testFailMessage);
	}

	public static Stream<Arguments> testData() {
		return Stream.of(
				Arguments.of(
						ConversionType.YARDS_TO_METERS,
						ConversionType.METERS_TO_YARDS,
						10.0,
						"Yards to meters should be reversible"),
				Arguments.of(
						ConversionType.METERS_TO_YARDS,
						ConversionType.YARDS_TO_METERS,
						10.0,
						"Meters to yards should be reversible"),
				Arguments.of(
						ConversionType.KILOMETERS_TO_MILES,
						ConversionType.MILES_TO_KILOMETERS,
						10.0,
						"Kilometers to miles should be reversible"),
				Arguments.of(
						ConversionType.MILES_TO_KILOMETERS,
						ConversionType.KILOMETERS_TO_MILES,
						10.0,
						"Miles to kilometers should be reversible"),
				Arguments.of(
						ConversionType.INCHES_TO_CENTIMETERS,
						ConversionType.CENTIMETERS_TO_INCHES,
						10.0,
						"Inches to centimeters should be reversible"),
				Arguments.of(
						ConversionType.CENTIMETERS_TO_INCHES,
						ConversionType.INCHES_TO_CENTIMETERS,
						10.0,
						"Centimeters to inches should be reversible")
		);
	}
}