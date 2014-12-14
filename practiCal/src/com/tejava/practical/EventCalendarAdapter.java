package com.tejava.practical;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventCalendarAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<SingleEvent> dataList;
	
	public EventCalendarAdapter(Context context)
	{
		this.context = context;
		dataList = new ArrayList<SingleEvent>();
	}
	
	public void addItem(SingleEvent event)
	{
		dataList.add(event);
	}
	
	@Override
	public int getCount()
	{
		return dataList.size();
	}
	
	@Override
	public Object getItem(int index)
	{
		return dataList.get(index);
	}
	
	@Override
	public long getItemId(int index)
	{
		return index;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout itemView;
		SingleEvent data = dataList.get(position);
		
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			itemView = (LinearLayout) inflater.inflate(R.layout.item, null);
		} else {
			itemView = (LinearLayout) convertView;
		}
		
		TextView single_event_date_time = (TextView) itemView.findViewById(R.id.single_event_date_time);
		single_event_date_time.setText(new String(data.GetYear()+"-"+data.GetMonth()+"-"+data.GetDay()+" "+
				data.GetStartHour()+":"+data.GetStartMin()+"~"+data.GetEndHour()+":"+data.GetEndMin()));
		
		TextView single_event_name = (TextView) itemView.findViewById(R.id.single_event_name);
		single_event_name.setText(data.GetName());
		
		TextView single_event_description = (TextView) itemView.findViewById(R.id.single_event_description);
		single_event_description.setText(data.GetDescription());
		
		return itemView;
	}
}
