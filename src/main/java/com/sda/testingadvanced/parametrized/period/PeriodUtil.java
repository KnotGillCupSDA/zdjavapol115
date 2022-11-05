package com.sda.testingadvanced.parametrized.period;

import java.time.Instant;
import java.time.YearMonth;

public class PeriodUtil {

	/**
	 *
	 * When from is not null then
	 * if from is between 1st and 15th then month = that month
	 * if from is after 15th then month = next month
	 * otherwise
	 * if to is between 1st and 15th then month = previous month
	 * if to is after 15th then month = next month
	 *
	 * @param from beginning of the period
	 * @param to end of the period
	 * @return classified to year-month
	 * @throws java.lang.IllegalArgumentException when both dates are null or to is before from
	 */
	public static YearMonth getPeriod(Instant from, Instant to) {
		return null;
	}

}
