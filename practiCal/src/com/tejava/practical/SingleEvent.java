package com.tejava.practical;

public class SingleEvent
{

	private int id;
	private int year, month, day;
	private int startHour, endHour;
	private int startMin, endMin;
	private String name, description;

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
}
