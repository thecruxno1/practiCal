package com.tejava.practical;

import java.util.ArrayList;
import java.util.Calendar;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MonthlyCalendarAdapter extends BaseAdapter {

	public static final String TAG = "MonthlyCalendarAdapter";

	Context mContext;

	public static int oddColor = Color.rgb(225, 225, 225);
	public static int headColor = Color.rgb(12, 32, 158);

	private int selectedPosition = -1;

	private MonthlyCalendarOneDay[] items;

	private int countColumn = 7;

	int mStartDay;
	int startDay;
	int curYear;
	int curMonth;

	int firstDay;
	int lastDay;
	
	int dayOfWeek;
	
	Calendar mCalendar;
	boolean recreateItems = false;

	EventList list = new EventList(mContext);
	
	
	public MonthlyCalendarAdapter(Context context) {
		super();
		mContext = context;
		init();
	}

	public MonthlyCalendarAdapter(Context context, AttributeSet attrs) {
		super();
		mContext = context;
		init();
	}

	private void init() {
		items = new MonthlyCalendarOneDay[7 * 6];
		mCalendar = Calendar.getInstance();
		recalculate();
		resetDayNumbers();
	}

	public void recalculate() {
		// set to the first day of the month
		mCalendar.set(Calendar.DAY_OF_MONTH, 1);

		// get week day
		int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
		firstDay = getFirstDay(dayOfWeek);

		mStartDay = mCalendar.getFirstDayOfWeek();
		curYear = mCalendar.get(Calendar.YEAR);
		curMonth = mCalendar.get(Calendar.MONTH);
		lastDay = getMonthLastDay(curYear, curMonth);

		int diff = mStartDay - Calendar.SUNDAY - 1;
		startDay = getFirstDayOfWeek();
	}

	public void setPreviousMonth() {
		mCalendar.add(Calendar.MONTH, -1);
		recalculate();

		resetDayNumbers();
		selectedPosition = -1;
	}

	public void setNextMonth() {
		mCalendar.add(Calendar.MONTH, 1);
		recalculate();

		resetDayNumbers();
		selectedPosition = -1;
	}

	public void setSpecificMonth(int newYear, int newMonth, int newDay){
		mCalendar.set(newYear, newMonth, newDay);
		recalculate();
		
		resetDayNumbers();
		
		selectedPosition=0;
		for(int i=0;i<42;i++)
		{
			if(items[i].getDay() != newDay)
				selectedPosition++;
			else{
				break;
			}	
		}
	}
	
	public void resetDayNumbers() {
		for (int i = 0; i < 42; i++) {
			// calculate day number
			int dayNumber = (i + 1) - firstDay;
			if (dayNumber < 1 || dayNumber > lastDay) {
				dayNumber = 0;
			}
			// save as a data item
			items[i] = new MonthlyCalendarOneDay(dayNumber, curMonth + 1,
					curYear);
		}
	}

	private int getFirstDay(int dayOfWeek) {
		int result = 0;
		if (dayOfWeek == Calendar.SUNDAY) {
			result = 0;
		} else if (dayOfWeek == Calendar.MONDAY) {
			result = 1;
		} else if (dayOfWeek == Calendar.TUESDAY) {
			result = 2;
		} else if (dayOfWeek == Calendar.WEDNESDAY) {
			result = 3;
		} else if (dayOfWeek == Calendar.THURSDAY) {
			result = 4;
		} else if (dayOfWeek == Calendar.FRIDAY) {
			result = 5;
		} else if (dayOfWeek == Calendar.SATURDAY) {
			result = 6;
		}

		return result;
	}

	public int getCurYear() {
		return curYear;
	}

	public int getCurMonth() {
		return curMonth;
	}

	public int getNumColumns() {
		return 7;
	}

	public int getCount() {
		return 7 * 6;
	}

	public Object getItem(int position) {
		return items[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		MonthlyCalendarOneDayView itemView;

		if (convertView == null) {
			itemView = new MonthlyCalendarOneDayView(mContext);
		} else {
			itemView = (MonthlyCalendarOneDayView) convertView;
		}

		// create a params
		GridView.LayoutParams params = new GridView.LayoutParams(
				GridView.LayoutParams.MATCH_PARENT, 120);

		// calculate row and column
		int rowIndex = position / countColumn;
		int columnIndex = position % countColumn;

		// set item data and properties
		itemView.setItem(items[position]);
		itemView.setLayoutParams(params);
		itemView.setPadding(2, 2, 2, 2);

		// set properties
		//itemView.setGravity(Gravity.LEFT);

		if (columnIndex == 0) {
			itemView.dayValue.setTextColor(Color.RED);
		} else if (columnIndex == 6) {
			itemView.dayValue.setTextColor(Color.BLUE);
		} else {
			itemView.dayValue.setTextColor(Color.BLACK);
		}
		items[position].setDayOfWeek(columnIndex);
		
		// set background color
		if (position == getSelectedPosition()) {
			if(items[position].getDay()!=0)
			itemView.setBackgroundColor(Color.rgb(2, 217, 255));
		} else {
			itemView.setBackgroundColor(Color.WHITE);
		}
		
		// FIXME
		// get database
		// remove db
		// insert db
		ArrayList<SingleEvent> templist = list.Search(items[position].getYear(), items[position].getMonth(), items[position].getDay());
		if (templist.size() == 0) {
			itemView.eventAlert.setText("");
		} else {
			itemView.eventAlert.setText(templist.size()+" event exists");
			itemView.eventAlert.setVisibility(View.VISIBLE);
		}
		
		return itemView;
	}

	public static int getFirstDayOfWeek() {
		int startDay = Calendar.getInstance().getFirstDayOfWeek();
		if (startDay == Calendar.SATURDAY) {
			return Time.SATURDAY;
		} else if (startDay == Calendar.MONDAY) {
			return Time.MONDAY;
		} else {
			return Time.SUNDAY;
		}
	}

	private int getMonthLastDay(int year, int month) {
		switch (month) {
		case 0:
		case 2:
		case 4:
		case 6:
		case 7:
		case 9:
		case 11:
			return (31);

		case 3:
		case 5:
		case 8:
		case 10:
			return (30);

		default:
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
				return (29);
			} else {
				return (28);
			}
		}
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setEventlist(EventList eventList) {
		this.list = eventList;
	}
}