package com.tejava.practical;

import java.util.Vector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class MonthlyCalendarOneDayAdapter extends ArrayAdapter<String>{

	Context mContext;
	public Vector<String> singleEventList;
	
	public MonthlyCalendarOneDayAdapter(Context context) {
		super(context, android.R.layout.simple_list_item_1);
		mContext = context;
		init();
	}

	public MonthlyCalendarOneDayAdapter(Context context, AttributeSet attrs) {
		super(context, android.R.layout.simple_list_item_1);
		mContext = context;
		init();
	}
	
	private void init() {
		singleEventList = new Vector<String>();
		//from google
		//get the data;
	}
	
	@Override
	public int getCount() {
		return singleEventList.size();
	}

	@Override
	public String getItem(int position) {
		return singleEventList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		MonthlyCalendarOneDayView itemview;
		
		
		if (convertView == null) {
			itemview = new MonthlyCalendarOneDayView(mContext);
		} else {
			itemview = (MonthlyCalendarOneDayView) convertView;
		}
		MonthlyCalendarOneDay temp = itemview.getItem();
		
		
		if(temp.getMonth()==12 && temp.getDay()==25)
		{
			
		}
		return itemview;

	}

}
