package com.tejava.practical;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class SeveralCalendarAdapter extends BaseAdapter {

	Context mContext;

	private int selectedPosition = -1;

	private SeveralCalendarOneDay[] items;

	Calendar mCalendar;
	int curYear;
	int curMonth;
	int curDay;

	int severalNumber = 7;
	
	EventList list = new EventList(mContext);

	public SeveralCalendarAdapter(Context context) {
		super();
		mContext = context;
		init();
	}

	public SeveralCalendarAdapter(Context context, AttributeSet attrs) {
		super();
		mContext = context;
		init();
	}

	private void init() {
		items = new SeveralCalendarOneDay[severalNumber];
		mCalendar = Calendar.getInstance();
		curYear = mCalendar.get(Calendar.YEAR);
		curMonth = mCalendar.get(Calendar.MONTH);
		curDay = mCalendar.get(Calendar.DATE);
		recalculate(curYear, curMonth, curDay);
	}

	public void recalculate(int year, int month, int day) {
		mCalendar.set(year, month, day);
		for (int i = 0; i < severalNumber; i++) {
			
				items[i] = new SeveralCalendarOneDay(
						mCalendar.get(Calendar.DATE),
						mCalendar.get(Calendar.MONTH) + 1,
						mCalendar.get(Calendar.YEAR));
			
			items[i].setDayOfWeek(mCalendar.get(Calendar.DAY_OF_WEEK) - 1);
			mCalendar.add(mCalendar.DATE, 1);
		}
		
		selectedPosition=0;
		for(int i=0;i<severalNumber;i++)
		{
			if(items[i].getDay() != day)
				selectedPosition++;
			else{
				break;
			}	
		}
	}

	public int getCurYear() {
		return curYear;
	}

	public int getCurMonth() {
		return curMonth;
	}

	public int getCount() {
		return severalNumber;
	}

	public Object getItem(int position) {
		return items[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		SeveralCalendarOneDayView itemView;

		if (convertView == null) {
			itemView = new SeveralCalendarOneDayView(mContext);
		} else {
			itemView = (SeveralCalendarOneDayView) convertView;
		}

		GridView.LayoutParams params = new GridView.LayoutParams(
				GridView.LayoutParams.MATCH_PARENT, 120);

		itemView.setItem(items[position]);
		itemView.setLayoutParams(params);
		itemView.setPadding(2, 2, 2, 2);

		if (items[position].getDayOfWeek() == 0) {
			itemView.dateString.setTextColor(Color.RED);
		} else if (items[position].getDayOfWeek() == 6) {
			itemView.dateString.setTextColor(Color.BLUE);
		} else {
			itemView.dateString.setTextColor(Color.BLACK);
		}

		// set background color
		if (position == getSelectedPosition()) {
			if (items[position].getDay() != 0)
				itemView.setBackgroundColor(Color.rgb(2, 217, 255));
		} else {
			itemView.setBackgroundColor(Color.WHITE);
		}

		// FIXME
		// get database
		// remove db
		// insert db

		ArrayList<SingleEvent> templist = list.Search(
				items[position].getYear(), items[position].getMonth(),
				items[position].getDay());
		if (templist.size() == 0) {
			itemView.eventAlertString.setText("");
		} else {
			itemView.eventAlertString
					.setText(templist.size() + " event exists");
			itemView.eventAlertString.setVisibility(View.VISIBLE);
		}
		return itemView;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setEventList(EventList eventlist) {
		this.list = eventlist;
	}
	public void setSeveralNumber(int num){
		this.severalNumber = num;
	}
	
	public int getSeveralNumber(){
		return severalNumber;
	}

}
