package com.tejava.practical;

public class SingleEvent
{

	private int id, year, month, day, startHour, endHour, startMin, endMin;
	private String name, description;

	public void SingEvent()
	{

	}

	private int getMonthLastDay()
	{
		switch (month) {
		case 0:
		case 2:
		case 4:
		case 6:
		case 7:
		case 9:
		case 11:
			return (31);

		case 3:
		case 5:
		case 8:
		case 10:
			return (30);

		default:
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
				return (29);
			} else {
				return (28);
			}
		}
	}

	public void SetId(int newID) throws Exception
	{
		try
		{
			if(newID < 0)
			{
				throw new Exception();
			}
			id = newID;
		}
		catch (Exception ex)
		{
			System.out.println("ID is " + newID + ", ID should be positive!");
		}
	}
	
	public int GetId()
	{
		return id;
	}

	public void SetYear(int newYear) throws Exception
	{
		try
		{
			if(newYear < 0)
			{
				throw new Exception();
			}
			year = newYear;
		}
		catch (Exception ex)
		{
			System.out.println("Year is " + newYear + ", year should be positive!");
		}
	}
	
	public int GetYear()
	{
		return year;
	}

	public void SetMonth(int newMonth) throws Exception
	{
		try
		{
			if((newMonth < 0) || (newMonth > 12))
			{
				throw new Exception();
			}
			month = newMonth;
		}
		catch (Exception ex)
		{
			System.out.println("Month is " + newMonth + ", month should be over 1 and below 12!");
		}
	}
	
	public int GetMonth()
	{
		return month;
	}

	public void SetDay(int newDay) throws Exception
	{
		try
		{
			if((newDay < 0) || (newDay > getMonthLastDay()))
			{
				throw new Exception();
			}
			day = newDay;
		}
		catch (Exception ex)
		{
			System.out.println("Day is " + newDay + ", WRONG!!");
		}
	}
	
	public int GetDay()
	{
		return day;
	}
}
