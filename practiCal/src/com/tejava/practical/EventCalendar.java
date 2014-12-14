package com.tejava.practical;

public class EventCalendar {
	private int eventNumber;
	
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
