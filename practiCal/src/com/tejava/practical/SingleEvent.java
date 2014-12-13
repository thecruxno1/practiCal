package com.tejava.practical;

public class SingleEvent
{

	private int id, year, month, day, startHour, endHour, startMin, endMin;
	private String name, description;

	public void SingEvent()
	{

	}

	public void SetId(int id) throws Exception
	{
		try
		{
			if(id < 0)
			{
				throw new Exception();
			}
		}
		catch (Exception ex)
		{
			System.out.println("ID is " + id + ", ID should be positive!");
		}
	}
	
	public int GetId()
	{
		return id;
	}

}
