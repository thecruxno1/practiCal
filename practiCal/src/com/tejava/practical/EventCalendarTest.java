package com.tejava.practical;

import static org.junit.Assert.*;

import org.junit.Test;


public class EventCalendarTest {

	private int testdat1;


	
	
	@Test
	public void testSetEventNumber() {
		EventCalendar EventCalendar1 = new EventCalendar();
		EventCalendar1.SetEventNumber(5);
		assertEquals(5, EventCalendar1.eventNumber);
	}

	@Test
	public void testGetEventNumber() {
		EventCalendar EventCalendar1 = new EventCalendar();
		testdat1 = EventCalendar1.GetEventNumber();
		assertEquals(10, testdat1);
	}

}
