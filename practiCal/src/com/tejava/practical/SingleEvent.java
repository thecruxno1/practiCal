package com.tejava.practical;

public class SingleEvent
{

	private int id;
	private int year, month, day;
	private int startHour, endHour;
	private int startMin, endMin;
	private String name, description;
	
	public SingleEvent(SingleEvent newSingleEvent) throws Exception
	{
		Copy(newSingleEvent);
	}
	
	public SingleEvent(int newId, int newYear, int newMonth, int newDay,
			int newStartHour, int newEndHour, int newStartMin, int newEndMin,
			String newName, String newDescription) throws Exception
	{
		Copy(newId, newYear, newMonth, newDay,
				newStartHour, newEndHour, newStartMin, newEndMin,
				newName, newDescription);
	}
	
	public void Copy(SingleEvent newSingleEvent) throws Exception
	{
		SetId(newSingleEvent.GetId());
		SetYear(newSingleEvent.GetYear());
		SetMonth(newSingleEvent.GetMonth());
		SetDay(newSingleEvent.GetDay());
	}
	
	public void Copy(int newId, int newYear, int newMonth, int newDay,
			int newStartHour, int newEndHour, int newStartMin, int newEndMin,
			String newName, String newDescription) throws Exception
	{
		SetId(newId);
		SetYear(newYear);
		SetMonth(newMonth);
		SetDay(newDay);
		SetStartHour(newStartHour);
		SetEndHour(newEndHour);
		SetStartMin(newStartMin);
		SetEndMin(newEndMin);
		SetName(newName);
		SetDescription(newDescription);
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

	public void SetId(int newId) throws Exception
	{
		try
		{
			if(newId < 0)
			{
				throw new Exception();
			}
			
			id = newId;
		}
		catch (Exception ex)
		{
			System.out.println("ID is " + newId + ", ID should be positive!");
		}
	}
	
	public int GetId()
	{
		return id;
	}
	
	public void SetStartHour(int newStartHour) throws Exception
	{
		try
		{
			if(newStartHour < 0 || newStartHour > 23 || newStartHour > endHour)
			{
				throw new Exception();
			}
			
			startHour = newStartHour;
		}
		catch (Exception ex)
		{
			System.out.println("starHour is " + newStartHour + ", invalid startHour!");
		}
	}
	
	public int GetStartHour()
	{
		return startHour;
	}
	
	public void SetEndHour(int newEndHour) throws Exception
	{
		try
		{
			if(newEndHour < 0 || newEndHour > 23 || newEndHour < startHour)
			{
				throw new Exception();
			}
			
			endHour = newEndHour;
		}
		catch (Exception ex)
		{
			System.out.println("endHour is " + newEndHour + ", invalid endHour!");
		}
	}
	
	public int GetEndHour()
	{
		return endHour;
	}
	
	public void SetStartMin(int newStartMin) throws Exception
	{
		try
		{
			if(newStartMin < 0 || newStartMin > 59 || newStartMin > endMin)
			{
				throw new Exception();
			}
			
			startMin = newStartMin;
		}
		catch (Exception ex)
		{
			System.out.println("starHour is " + newStartMin + ", invalid startMin!");
		}
	}
	
	public int GetStartMin()
	{
		return startMin;
	}
	
	public void SetEndMin(int newEndMin) throws Exception
	{
		try
		{
			if(newEndMin < 0 || newEndMin >23 || newEndMin < startMin)
			{
				throw new Exception();
			}
			
			endMin = newEndMin;
		}
		catch (Exception ex)
		{
			System.out.println("endMin is " + newEndMin + ", invalid endMin!");
		}
	}
	
	public int GetEndMin()
	{
		return endMin;
	}
	
	public void SetName(String newName) throws Exception
	{
		try
		{
			if(newName == "")
				throw new Exception();
			
			name = newName;
		}
		catch (Exception ex)
		{
			System.out.println("name should not be empty!");
		}
	}
	
	public String GetName()
	{
		return name;
	}
	
	public void SetDescription(String newDescription)
	{
		description = newDescription;
	}
	
	public String GetDescription()
	{
		return description;
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
