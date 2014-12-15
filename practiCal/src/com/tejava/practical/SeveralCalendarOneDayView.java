package com.tejava.practical;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SeveralCalendarOneDayView extends LinearLayout {

	SeveralCalendarOneDay item;
	TextView dateString;
	TextView dayValueString;
	TextView eventAlertString;
	
	
	public SeveralCalendarOneDayView(Context context){
		super(context);
		init(context);
	}
	
	public SeveralCalendarOneDayView(Context context, AttributeSet attrs){
		super(context, attrs);
		init(context);
	}
	
	public void init(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.several_calendar_oneday_view, this, true);
		
		dateString = (TextView) findViewById(R.id.severalcalendar_oneday_date);
		dayValueString = (TextView) findViewById(R.id.severalcalendar_oneday_dayvalue);
		eventAlertString = (TextView) findViewById(R.id.severalcalendar_oneday_eventalart);
	}
	
	public SeveralCalendarOneDay getItem(){
		return item;
	}
	
	public void setItem(SeveralCalendarOneDay item){
		this.item = item;
		int day = item.getDay();
		String date = item.getDayofWeekString();
		
		if(day!=0){
			dayValueString.setText(String.valueOf(day));
			dateString.setText(date);
		}
	}

}
