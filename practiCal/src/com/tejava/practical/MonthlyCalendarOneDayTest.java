package com.tejava.practical;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MonthlyCalendarOneDayTest {

	@Before
	public void setUp() throws Exception {
		
	}


	@Test
	public void testMonthlyCalendarOneDayInt() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(12);
		
		assertEquals(12, MonthlyCalendarOneDay1.getDay());
	}

	@Test
	public void testMonthlyCalendarOneDayIntIntInt() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
		
		assertEquals(18, MonthlyCalendarOneDay1.getDay());
		assertEquals(12, MonthlyCalendarOneDay1.getMonth());
		assertEquals(14, MonthlyCalendarOneDay1.getYear());
	}

	@Test
	public void testGetDay() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
		
		assertEquals(18, MonthlyCalendarOneDay1.getDay());
	}

	@Test
	public void testSetDay() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
		MonthlyCalendarOneDay1.setDay(11);
		assertEquals(11, MonthlyCalendarOneDay1.getDay());
	}

	@Test
	public void testGetMonth() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
		
		assertEquals(12, MonthlyCalendarOneDay1.getMonth());
	}

	@Test
	public void testSetMonth() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
		MonthlyCalendarOneDay1.setMonth(11);
		assertEquals(11, MonthlyCalendarOneDay1.getMonth());

	}

	@Test
	public void testGetYear() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
		
		assertEquals(14, MonthlyCalendarOneDay1.getYear());

	}

	@Test
	public void testSetYear() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
		MonthlyCalendarOneDay1.setYear(11);
		assertEquals(11, MonthlyCalendarOneDay1.getYear());

	}

	@Test
	public void testGetDayOfWeek() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
		MonthlyCalendarOneDay1.setDayOfWeek(5);
		assertEquals(5, MonthlyCalendarOneDay1.getDayOfWeek());

	}

	@Test
	public void testSetDayOfWeek() {
		MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
		MonthlyCalendarOneDay1.setDayOfWeek(6);
		assertEquals(6, MonthlyCalendarOneDay1.getDayOfWeek());

	}

	@Test
	public void testSetDayofWeekString() {
		int dat;
		String Stringdat;
		for(dat=0;dat<7;dat++){
			MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
			MonthlyCalendarOneDay1.setDayOfWeek(dat);
			MonthlyCalendarOneDay1.setDayofWeekString(dat);
			if (dat == 0) {
				Stringdat = "SUN";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 1) {
				Stringdat = "MON";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 2) {
				Stringdat = "TUE";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 3) {
				Stringdat = "WED";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 4) {
				Stringdat = "THU";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 5) {
				Stringdat = "FRI";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 6) {
				Stringdat = "SAT";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			}
		}
	}

	@Test
	public void testGetDayofWeekString() {
		int dat;
		String Stringdat;
		for(dat=0;dat<7;dat++){
			MonthlyCalendarOneDay MonthlyCalendarOneDay1 = new MonthlyCalendarOneDay(18,12,14);
			MonthlyCalendarOneDay1.setDayOfWeek(dat);
			MonthlyCalendarOneDay1.setDayofWeekString(dat);
			if (dat == 0) {
				Stringdat = "SUN";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 1) {
				Stringdat = "MON";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 2) {
				Stringdat = "TUE";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 3) {
				Stringdat = "WED";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 4) {
				Stringdat = "THU";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 5) {
				Stringdat = "FRI";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			} else if (dat == 6) {
				Stringdat = "SAT";
				assertEquals(Stringdat, MonthlyCalendarOneDay1.getDayofWeekString());
			}
		}
	
	}

}
