package com.tejava.practical;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class EventCalendarView extends LinearLayout {
	// view
	Button startDay;
	Button endDay;
	Button printEventRange;
	
	TextView upcoming;
	EditText eventNumber;
	Button printEventNumber;
	
	ListView eventList;
	
	// data
	EventCalendar eventCalendar;
	
	// adapter
	EventListAdapter eventListAdapter;
	
	// define
	static final int EVENT_MOD_START_DATE_DIALOG_ID = 200;
	static final int EVENT_MOD_END_DATE_DIALOG_ID = 210;
	
	public EventCalendarView(Context context) {
		super(context);
		init(context);
	}
	
	public EventCalendarView(Context context, AttributeSet attrs) {
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
	
	private void variableInitialize()
	{
		// views
		startDay = (Button) findViewById(R.id.start_day);
		endDay = (Button) findViewById(R.id.end_day);
		printEventRange = (Button) findViewById(R.id.print_event_range);
		
		upcoming = (TextView) findViewById(R.id.upcoming);
		eventNumber = (EditText) findViewById(R.id.event_number);
		printEventNumber = (Button) findViewById(R.id.print_event_number);
		
		eventList = (ListView) findViewById(R.id.eventcalendar_eventlist);
	}

	private void listenerInitialize()
	{
		startDay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(EVENT_MOD_START_DATE_DIALOG_ID);
			}
		});
		
		endDay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(EVENT_MOD_END_DATE_DIALOG_ID);
			}
		});
		
		printEventRange.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				eventListAdapter = new EventListAdapter(this);
//				
//				ArrayList<SingleEvent> list = eventList2.Search(2014, 2, 3);
//				
//				for(int i=0; i<list.size(); ++i)
//					eventCalAdapter.addItem(list.get(i));
				
				eventList.setAdapter(eventListAdapter);
			}
		});
	}
	
	// date picker
	private DatePickerDialog.OnDateSetListener mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			eventCalendar.startYear = year;
			eventCalendar.startMonth = monthOfYear;
			eventCalendar.startDay = dayOfMonth;
			
			startDay.setText(eventCalendar.startYear+"/"+(int)(eventCalendar.startMonth+1)+"/"+eventCalendar.startDay);
		}
	};
	private DatePickerDialog.OnDateSetListener mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			eventCalendar.endYear = year;
			eventCalendar.endMonth = monthOfYear;
			eventCalendar.endDay = dayOfMonth;
			
			endDay.setText(eventCalendar.endYear+"/"+(int)(eventCalendar.endMonth+1)+"/"+eventCalendar.endDay);
		}
	};
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case EVENT_MOD_START_DATE_DIALOG_ID:
			return new DatePickerDialog(this, mStartDateSetListener, start_year, start_month, start_day);
		case EVENT_MOD_START_TIME_DIALOG_ID:
			return new TimePickerDialog(this, mStartTimeSetListener, start_hour, start_min, true);
		case EVENT_MOD_END_DATE_DIALOG_ID:
			return new DatePickerDialog(this, mEndDateSetListener, end_year, end_month, end_day);
		case EVENT_MOD_END_TIME_DIALOG_ID:
			return new TimePickerDialog(this, mEndTimeSetListener, end_hour, end_min, true);
		}

		return null;
	}
}