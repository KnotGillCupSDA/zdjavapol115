package com.sda.testingadvanced.parametrized.period;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PeriodUtilTest {

	@ParameterizedTest
	@MethodSource
	void shouldClassifyToPeriod(Instant from, Instant to, YearMonth expectedYearMonth) {
		assertThat(PeriodUtil.getPeriod(from, to)).isEqualTo(expectedYearMonth);
	}

	@Test
	void shouldThrowExceptionWhenBothDatesAreNull() {
		Assertions.assertThrows(RuntimeException.class, () -> PeriodUtil.getPeriod(null, null));
		Assertions.assertThrows(IllegalArgumentException.class, () -> PeriodUtil.getPeriod(null, null));

		assertThatThrownBy(() -> PeriodUtil.getPeriod(null, null)).isInstanceOf(IllegalArgumentException.class);
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> PeriodUtil.getPeriod(null, null));

	}

	public static Stream<Arguments> shouldClassifyToPeriod() {
		return Stream.of(
				Arguments.of(
						getInstant(2007, 12, 3),
						getInstant(2007, 12, 12),
						YearMonth.of(2007, 12)),
				Arguments.of(
						getInstant(2007, 12, 3),
						null,
						YearMonth.of(2007, 12)),
				Arguments.of(
						getInstant(2007, 12, 15),
						getInstant(2007, 12, 31),
						YearMonth.of(2007, 12)),
				Arguments.of(
						getInstant(2007, 12, 16),
						getInstant(2007, 12, 31),
						YearMonth.of(2008, 1)),
				Arguments.of(
						getInstant(2007, 11, 27),
						getInstant(2007, 12, 12),
						YearMonth.of(2007, 12)),
				Arguments.of(
						getInstant(2007, 11, 3),
						getInstant(2007, 12, 18),
						YearMonth.of(2007, 11)),
				Arguments.of(
						null,
						getInstant(2007, 12, 5),
						YearMonth.of(2007, 11)),
				Arguments.of(
						null,
						getInstant(2007, 12, 15),
						YearMonth.of(2007, 11)),
				Arguments.of(
						null,
						getInstant(2007, 12, 16),
						YearMonth.of(2007, 12)),
				Arguments.of(
						null,
						getInstant(2008, 1, 14),
						YearMonth.of(2007, 12))
		);
	}

	private static Instant getInstant(int year, int month, int dayOfMonth) {
		return LocalDate.of(year, month, dayOfMonth).atStartOfDay().toInstant(ZoneOffset.UTC);
	}
}