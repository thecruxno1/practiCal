package com.tejava.practical;



import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MonthlyCalendarOneDayView extends LinearLayout{

	MonthlyCalendarOneDay item;
	TextView dayValue;
	ListView singleEventList;
//	ArrayAdapter<String> adapter;
	MonthlyCalendarOneDayAdapter adapter;
	
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
//		adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
	//	singleEventList.setAdapter(adapter);
		
		adapter = new MonthlyCalendarOneDayAdapter(context);
		singleEventList.setAdapter(adapter);
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
