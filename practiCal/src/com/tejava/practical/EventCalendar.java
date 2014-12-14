package com.tejava.practical;

public class EventCalendar {
	public int startYear;
	public int startMonth;
	public int startDay;
	
	public int endYear;
	public int endMonth;
	public int endDay;
	
	public int eventNumber;
	
	public EventCalendar()
	{
		eventNumber = 10;
	}
	
	public void SetEventNumber(int newEventNumber)
	{
		eventNumber = newEventNumber;
	}
	
	public int GetEventNumber()
	{
		return eventNumber;
	}
}
