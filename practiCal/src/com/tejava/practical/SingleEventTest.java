package com.tejava.practical;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingleEventTest{

	//public SingleEvent SingleEvent1;
	//public SingleEvent SingleEvent2;
	//public SingleEvent SingleEvent3;

	
	@Test
	public void testSingleEvent() throws Exception{
		
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetYear(14);

		SingleEvent SingleEvent2 = new SingleEvent();
		SingleEvent2.SetYear(15);

		assertEquals(14, SingleEvent1.GetYear());
		
		assertEquals(15, SingleEvent2.GetYear());
	}


	@Test
	public void testCopySingleEvent() throws Exception{
		
		SingleEvent SingleEvent3 = new SingleEvent(3, 2011, 12, 25, 12, 14, 00, 30, "test1", "Description", 1, 1, 1, "Location");
		

		assertEquals(2011, SingleEvent3.GetYear());
	}



	@Test
	public void testSetStartYearMonthDayAndTime() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetYear(14);
		SingleEvent1.SetMonth(12);
		SingleEvent1.SetDay(12);
		SingleEvent1.SetStartHour(12);
		SingleEvent1.SetStartMin(12);
		SingleEvent1.SetStartYearMonthDayAndTime();
		long dat1 = 1412121212;

		assertEquals(dat1, SingleEvent1.GetStartYearMonthDayAndTime());
	}

	@Test
	public void testSetStartYearMonthDayAndTimeLong() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		
		long dat1 = 1412121212;
		SingleEvent1.SetStartYearMonthDayAndTime(dat1);
		
		assertEquals(dat1, SingleEvent1.GetStartYearMonthDayAndTime());
	}

	@Test
	public void testSetEndYearMonthDayAndTime()throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetYear(14);
		SingleEvent1.SetMonth(12);
		SingleEvent1.SetDay(18);
		SingleEvent1.SetEndHour(12);
		SingleEvent1.SetEndMin(18);
		SingleEvent1.SetEndYearMonthDayAndTime();
		long dat2 = 1412181218;
		assertEquals(dat2, SingleEvent1.GetEndYearMonthDayAndTime());
	}

	@Test
	public void testSetEndYearMonthDayAndTimeLong()throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		long dat2 = 1412181218;
		SingleEvent1.SetEndYearMonthDayAndTime(dat2);
		
		assertEquals(dat2, SingleEvent1.GetEndYearMonthDayAndTime());
	}

	@Test
	public void testSetYearMonthDay() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetYear(14);
		SingleEvent1.SetMonth(12);
		SingleEvent1.SetDay(12);
		SingleEvent1.SetYearMonthDay();
		int dat3 = 141212;
		assertEquals(dat3, SingleEvent1.GetYearMonthDay());
	}

	@Test
	public void testSetYearMonthDayInt() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		int dat3 = 141212;
		SingleEvent1.SetYearMonthDay(dat3);
		
		assertEquals(dat3, SingleEvent1.GetYearMonthDay());
	}

	@Test
	public void testGetYearMonthDay() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		int dat3 = 141212;
		SingleEvent1.SetYearMonthDay(dat3);
		assertEquals(dat3, SingleEvent1.GetYearMonthDay());
	}

	@Test
	public void testGetStartYearMonthDayAndTime() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		
		long dat1 = 1412121212;
		SingleEvent1.SetStartYearMonthDayAndTime(dat1);
		assertEquals(dat1, SingleEvent1.GetStartYearMonthDayAndTime());
	}

	@Test
	public void testGetEndYearMonthDayAndTime() throws Exception{

		SingleEvent SingleEvent1 = new SingleEvent();
		
		long dat2 = 1412181218;
		SingleEvent1.SetEndYearMonthDayAndTime(dat2);
		assertEquals(dat2, SingleEvent1.GetEndYearMonthDayAndTime());
	}

	@Test
	public void testSetId() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();

		SingleEvent1.SetId(3);
		assertEquals(3, SingleEvent1.GetId());
	}

	@Test
	public void testGetId() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetId(2);
		assertEquals(2, SingleEvent1.GetId());
	}

	@Test
	public void testSetStartHour() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetStartHour(3);
		assertEquals(3,SingleEvent1.GetStartHour());
	}
//
	@Test
	public void testGetStartHour() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetStartHour(2);
		assertEquals(2, SingleEvent1.GetStartHour());
	}

	@Test
	public void testSetEndHour() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetEndHour(3);
		assertEquals(3,SingleEvent1.GetEndHour());
	}

	@Test
	public void testGetEndHour() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetEndHour(2);
		assertEquals(2, SingleEvent1.GetEndHour());
	}

	@Test
	public void testSetStartMin() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetStartMin(30);
		assertEquals(30,SingleEvent1.GetStartMin());
	}

	@Test
	public void testGetStartMin() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetStartMin(2);
		assertEquals(2, SingleEvent1.GetStartMin());
	}

	@Test
	public void testSetEndMin() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetEndMin(35);
		assertEquals(35, SingleEvent1.GetEndMin());
	}

	@Test
	public void testGetEndMin() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetEndMin(2);
		assertEquals(2, SingleEvent1.GetEndMin());
	}

	@Test
	public void testSetName() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetName("test");
		assertEquals("test", SingleEvent1.GetName());
	}

	@Test
	public void testGetName() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetName("test");
		assertEquals("test",SingleEvent1.GetName());
	}

	@Test
	public void testSetDescription() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetDescription("test");
		assertEquals("test", SingleEvent1.GetDescription());
	}

	@Test
	public void testGetDescription() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetDescription("test");
		assertEquals("test",SingleEvent1.GetDescription());
	}

	@Test
	public void testSetYear() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		
		SingleEvent1.SetYear(14);
		assertEquals(14, SingleEvent1.GetYear());
	}

	@Test
	public void testGetYear() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetYear(14);
		assertEquals(14, SingleEvent1.GetYear());
	}

	@Test
	public void testSetMonth() throws Exception {

		SingleEvent SingleEvent1 = new SingleEvent();
		
		SingleEvent1.SetMonth(12);
		assertEquals(12, SingleEvent1.GetMonth());
	}

	@Test
	public void testGetMonth() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetMonth(12);
		assertEquals(12, SingleEvent1.GetMonth());
	}

	@Test
	public void testSetDay() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();		
		SingleEvent1.SetDay(14);
		assertEquals(14, SingleEvent1.GetDay());
	}

	@Test
	public void testGetDay() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetDay(12);
		assertEquals(12, SingleEvent1.GetDay());
	}

	@Test
	public void testSetEventGroup() throws Exception {
		SingleEvent SingleEvent1 = new SingleEvent();		
		SingleEvent1.SetEventGroup(8);
		assertEquals(8, SingleEvent1.GetEventGroup());
	}

	@Test
	public void testGetEventGroup() throws Exception{
		SingleEvent SingleEvent1 = new SingleEvent();
		SingleEvent1.SetEventGroup(1);
		assertEquals(1, SingleEvent1.GetEventGroup());
	}

	

}
