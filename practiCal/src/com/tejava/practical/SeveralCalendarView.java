package com.tejava.practical;

import com.tejava.practical.MonthlyCalendarView.OnItemClickAdapter;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SeveralCalendarView extends GridView{


	private OnDataSelectionListener selectionListener;
	SeveralCalendarAdapter adapter;
	
	int several_number = 7;

	
	public SeveralCalendarView(Context context) {
		super(context);
		init(context);
	}

	public SeveralCalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public void init(Context context){
		setBackgroundColor(Color.BLACK);
		setVerticalSpacing(1);
		setHorizontalSpacing(1);
		setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

		setNumColumns(several_number);
		setOnItemClickListener(new OnItemClickAdapter());
	}
	
	public void setAdapter(BaseAdapter adapter) {
		super.setAdapter(adapter);
		this.adapter = (SeveralCalendarAdapter) adapter;
	}

	public BaseAdapter getAdapter() {
		return (BaseAdapter) super.getAdapter();
	}

	public void setOnDataSelectionListener(OnDataSelectionListener listener) {
		this.selectionListener = listener;
	}
	
	public OnDataSelectionListener getOnDataSelectionListener() {
		return selectionListener;
	}

	class OnItemClickAdapter implements OnItemClickListener {
		public OnItemClickAdapter() {
		}

		public void onItemClick(AdapterView parent, View v, int position,
				long id) {

			if (adapter != null) {
				adapter.setSelectedPosition(position);
				adapter.notifyDataSetInvalidated();
			}

			if (selectionListener != null) {
				selectionListener.onDataSelected(parent, v, position, id);
			}

		}
	}
	public void setSeveralNumber(int num){
		this.several_number = num;
	}
	public int getSeveralNumber(){
		return several_number;
	}
}
