package com.tejava.practical;

import java.util.Comparator;

public class CustomComparator implements Comparator<SingleEvent>
{
	@Override
	public int compare(SingleEvent event1, SingleEvent event2)
	{
		if(event1.GetStartYearMonthDayAndTime() < event2.GetStartYearMonthDayAndTime())
		{
			return -1;
		}
		else if(event1.GetStartYearMonthDayAndTime() > event2.GetStartYearMonthDayAndTime())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}
