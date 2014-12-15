package com.tejava.practical;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;


public class SeveralCalendarEventList
{
	private ArrayList<SingleEvent> eventList;
	private SingleEvent singleEvent;
	Context context = null;
	
	public SeveralCalendarEventList(Context newContext)
	{
		eventList = new ArrayList<SingleEvent>();
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
	
	public void Delete(int delId) throws Exception
	{
		int i;
		for(i = 0; i < eventList.size(); i++)
		{
			singleEvent = eventList.get(i);
			if(singleEvent.GetId() == delId)
			{
				eventList.remove(i);
				break;
			}
		}
		//Collections.sort(eventList, SingleEvent.startYearMonthDayAndTimeComparator);
		for(i = 0; i < eventList.size(); i++)
		{
			singleEvent = eventList.get(i);
			singleEvent.SetId(i);
			eventList.set(i, singleEvent);
		}
	}
	
	public void Delete(SingleEvent delSingleEvent) throws Exception
	{
		eventList.remove(delSingleEvent);
		Collections.sort(eventList, SingleEvent.startYearMonthDayAndTimeComparator);
		for(int i = 0; i < eventList.size(); i++)
		{
			singleEvent = eventList.get(i);
			singleEvent.SetId(i);
			eventList.add(i, singleEvent);
		}
	}
	
	public int GetSize()
	{
		return eventList.size();
	}
	
	public ArrayList<SingleEvent> Search(int targetYear, int targetMonth, int targetDay)
	{
		int targetYearMonthDay = (targetYear * 10000) + (targetMonth * 100) + targetDay;
		ArrayList<SingleEvent> newEventList = new ArrayList<SingleEvent>();
		
		for(int i = 0; i < eventList.size(); i++)
		{
			singleEvent = eventList.get(i);
			if(targetYearMonthDay == singleEvent.GetYearMonthDay())
			{
				newEventList.add(singleEvent);
			}
		}

		return newEventList;
	}
	
	public ArrayList<SingleEvent> Search(int targetYear1, int targetMonth1, int targetDay1,
			int targetYear2, int targetMonth2, int targetDay2)
	{
		int targetYearMonthDay1 = (targetYear1 * 10000) + (targetMonth1 * 100) + targetDay1;
		int targetYearMonthDay2 = (targetYear2 * 10000) + (targetMonth2 * 100) + targetDay2;
		int yearMonthDay;
		ArrayList<SingleEvent> newEventList = new ArrayList<SingleEvent>();
		
		for(int i = 0; i < eventList.size(); i++)
		{
			singleEvent = eventList.get(i);
			yearMonthDay = singleEvent.GetYearMonthDay();
			if(yearMonthDay >= targetYearMonthDay1)
			{
				if(yearMonthDay <= targetYearMonthDay2)
				{
					newEventList.add(singleEvent);
				}
			}
			else if(yearMonthDay <= targetYearMonthDay1)
			{
				if(yearMonthDay >= targetYearMonthDay2)
				{
					newEventList.add(singleEvent);
				}
			}
		}

		return newEventList;
	}
	
	public ArrayList<SingleEvent> Search(int targetYear, int targetMonth, int targetDay, int numberofEvents)
	{
		int targetYearMonthDay = (targetYear * 10000) + (targetMonth * 100) + targetDay;
		Collections.sort(eventList, SingleEvent.startYearMonthDayAndTimeComparator);
		ArrayList<SingleEvent> newEventList = new ArrayList<SingleEvent>();
		
		int count = 0;
		for(int i = 0; i < eventList.size(); i++)
		{
			singleEvent = eventList.get(i);
			if(targetYearMonthDay <= singleEvent.GetYearMonthDay())
			{
				newEventList.add(singleEvent);
				count += 1;
				if(count >= numberofEvents)
				{
					break;
				}
			}
		}
		return newEventList;
	}
	
	public void Save(String saveFileName) throws Exception
	{
		String fullPath = Environment.getExternalStorageDirectory().toString() + "/" + saveFileName;
		FileOutputStream fileOut;
		try
		{
			fileOut = new FileOutputStream(fullPath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(eventList);
			objectOut.close();
		}
		catch(Exception ex)
		{
			Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void Load(String loadFileName) throws Exception
	{

		String fullPath = Environment.getExternalStorageDirectory().toString() + "/" + loadFileName;
		FileInputStream fileIn;
		try
		{
			fileIn = new FileInputStream(fullPath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			eventList = (ArrayList<SingleEvent>) objectIn.readObject();
			objectIn.close();
		}
		catch(Exception ex)
		{
			Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}
