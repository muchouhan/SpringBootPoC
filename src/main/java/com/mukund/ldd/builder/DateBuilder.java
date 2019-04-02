package com.mukund.ldd.builder;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DateBuilder {

	private LocalDate localDate;

	public DateBuilder() {
		localDate = LocalDate.now();		
	}

	public String getStartDate() {
		return localDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
	}

	public String getEndDate() {
		return localDate.with(TemporalAdjusters.lastDayOfMonth()).toString();
	}
}
