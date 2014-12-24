package com.tejava.practical;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SeveralCalendarOneDayTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testSeveralCalendarOneDayInt() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(
				12);

		assertEquals(12, SeveralCalendarOneDay1.getDay());
	}

	@Test
	public void testSeveralCalendarOneDayIntIntInt() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(
				2014,11,12);

		assertEquals(12, SeveralCalendarOneDay1.getDay());
	}

	@Test
	public void testGetDay() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(
				12);

		assertEquals(12, SeveralCalendarOneDay1.getDay());
	}

	@Test
	public void testSetDay() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(
				2014,10,12);
		SeveralCalendarOneDay1.setDay(11);

		assertEquals(11, SeveralCalendarOneDay1.getDay());
	}

	@Test
	public void testGetMonth() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(
				2014,10,12);

		assertEquals(10, SeveralCalendarOneDay1.getMonth());
	}

	@Test
	public void testSetMonth() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(2014,10,12);
		SeveralCalendarOneDay1.setMonth(11);


		assertEquals(11, SeveralCalendarOneDay1.getMonth());
	}

	@Test
	public void testGetYear() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(2014,10,12);
		
		assertEquals(2014, SeveralCalendarOneDay1.getYear());
	}

	@Test
	public void testSetYear() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(2014,10,12);
		SeveralCalendarOneDay1.setYear(2011);

		
		assertEquals(2011, SeveralCalendarOneDay1.getYear());
	}

	@Test
	public void testGetDayOfWeek() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(18,12,14);
		SeveralCalendarOneDay1.setDayOfWeek(5);
		assertEquals(5, SeveralCalendarOneDay1.getDayOfWeek());
	}

	@Test
	public void testSetDayOfWeek() {
		SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(18,12,14);
		SeveralCalendarOneDay1.setDayOfWeek(3);
		SeveralCalendarOneDay1.setDayOfWeek(6);
		assertEquals(6, SeveralCalendarOneDay1.getDayOfWeek());
	}

	@Test
	public void testSetDayofWeekString() {
		int dat;
		String Stringdat;
		for(dat=0;dat<7;dat++){
			SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(18,12,14);
			SeveralCalendarOneDay1.setDayOfWeek(dat);
			SeveralCalendarOneDay1.setDayofWeekString(dat);
			if (dat == 0) {
				Stringdat = "SUN";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 1) {
				Stringdat = "MON";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 2) {
				Stringdat = "TUE";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 3) {
				Stringdat = "WED";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 4) {
				Stringdat = "THU";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 5) {
				Stringdat = "FRI";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 6) {
				Stringdat = "SAT";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			}
		}
	}

	@Test
	public void testGetDayofWeekString() {
		int dat;
		String Stringdat;
		for(dat=0;dat<7;dat++){
			SeveralCalendarOneDay SeveralCalendarOneDay1 = new SeveralCalendarOneDay(18,12,14);
			SeveralCalendarOneDay1.setDayOfWeek(dat);
			SeveralCalendarOneDay1.setDayofWeekString(dat);
			if (dat == 0) {
				Stringdat = "SUN";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 1) {
				Stringdat = "MON";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 2) {
				Stringdat = "TUE";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 3) {
				Stringdat = "WED";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 4) {
				Stringdat = "THU";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 5) {
				Stringdat = "FRI";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			} else if (dat == 6) {
				Stringdat = "SAT";
				assertEquals(Stringdat, SeveralCalendarOneDay1.getDayofWeekString());
			}
		}
	
	}

}
