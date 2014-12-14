package com.tejava.practical;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;


public class EventList
{
	private ArrayList<SingleEvent> eventList;
	private SingleEvent singleEvent;
	Context context = null;
	
	public EventList(Context newContext)
	{
		context = newContext;
	}
	
	public void Insert(SingleEvent newSingleEvent) throws Exception
	{
		eventList.add(newSingleEvent);
	}
	
	public void Insert(int newId, int newYear, int newMonth, int newDay,
				int newStartHour, int newEndHour, int newStartMin, int newEndMin,
				String newName, String newDescription) throws Exception
	{
		singleEvent = new SingleEvent(newId, newYear, newMonth, newDay,
				newStartHour, newEndHour, newStartMin, newEndMin,
				newName, newDescription);
		eventList.add(singleEvent);
	}
	
	public void Delete(int delId)
	{
		for(int i = 0; i < eventList.size(); i++)
		{
			singleEvent = eventList.get(i);
			if(singleEvent.GetId() == delId)
			{
				eventList.remove(i);
				break;
			}
		}
	}
	
	public void Delete(SingleEvent delSingleEvent)
	{
		eventList.remove(delSingleEvent);
	}
	
	public ArrayList<SingleEvent> Search(int targetYear, int targetMonth, int targetDay)
	{
		ArrayList<SingleEvent> newEventList = new ArrayList<SingleEvent>();
		
		for(int i = 0; i < eventList.size(); i++)
		{
			singleEvent = eventList.get(i);
			if((singleEvent.GetYear() == targetYear)
					&& (singleEvent.GetMonth() == targetMonth)
					&& (singleEvent.GetDay() == targetDay))
			{
				newEventList.add(singleEvent);
			}
		}

		return newEventList;
	}
	
	public void Save(String saveFileName) throws Exception
	{
		try
		{
			File file = new File(saveFileName);
			if(file.exists())
			{
				if(!file.delete())
				{
					throw new Exception("Can't delete file: " + saveFileName);
				}
				
			}
			if(!file.createNewFile())
			{
				throw new Exception("Can't create file: " + saveFileName);
			}
			if(!file.canWrite())
			{
				throw new Exception("Can't write file: "+ saveFileName);
			}
			FileOutputStream fileOut = context.openFileOutput(saveFileName, Context.MODE_PRIVATE);
			fileOut.write(1);
			fileOut.close();
			/*ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(eventList);
			objectOut.close();*/
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public void Load(String loadFileName) throws Exception
	{
		try
		{
			File file = new File(loadFileName);
			if(!file.exists())
			{
				throw new Exception("Does not exist file: " + loadFileName);		
			}
			if(!file.canRead())
			{
				throw new Exception("Can't read file: " + loadFileName);
			}
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			eventList = (ArrayList<SingleEvent>)objectIn.readObject();
			objectIn.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
