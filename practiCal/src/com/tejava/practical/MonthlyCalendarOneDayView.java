package com.tejava.practical;



import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MonthlyCalendarOneDayView extends LinearLayout{

	MonthlyCalendarOneDay item;
	TextView dayValue;
	ListView singleEventList;
	ArrayAdapter<String> adapter;
	
	public MonthlyCalendarOneDayView(Context context) {
		super(context);
		init(context);
	}
	
	public MonthlyCalendarOneDayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		setBackgroundColor(Color.BLACK);
	}
	
	public void init(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.monthly_calendar_oneday_view, this, true);
		
		dayValue = (TextView) findViewById(R.id.monthlycalendar_oneday_date);
		singleEventList = (ListView) findViewById(R.id.monthlycalendar_oneday_eventlist);
		
		adapter = new ArrayAdapter<String>(context, R.layout.monthlycalender_string);
		singleEventList.setAdapter(adapter);
	}
	
	public void setAdapter(BaseAdapter adapter) {
		this.adapter = (ArrayAdapter<String>) adapter;
	}
	
	public MonthlyCalendarOneDay getItem(){
		return item;
	}
	
	public void setItem(MonthlyCalendarOneDay item){
		this.item = item;
		int day = item.getDay();
		if (day != 0) {
			dayValue.setText(String.valueOf(day));
		} else {
			dayValue.setText("");
		}
	}
	
}
