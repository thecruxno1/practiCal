package com.tejava.practical;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class EventCalendarView extends LinearLayout {

	TextView upcoming;
	EditText eventNumber;
	Button printEventNumber;
	ListView eventList;
	
	EventCalendar eventCal;
	EventListAdapter eventListAdapter;
	
	public EventCalendarView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		upcoming = (TextView) findViewById(R.id.upcoming);
		eventNumber = (EditText) findViewById(R.id.event_number);
		printEventNumber = (Button) findViewById(R.id.print_event_number);
		eventList = (ListView) findViewById(R.id.eventcalendar_eventlist);
	}

}
