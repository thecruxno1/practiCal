package com.tejava.practical;

import android.view.View;
import android.widget.AdapterView;


public interface OnDataSelectionListener {

	/**
	 * 아이템이 선택되었을 때 호출되는 메소드
	 * 
	 * @param parent Parent View
	 * @param v Target View
	 * @param row Row Index
	 * @param column Column Index
	 * @param id ID for the View
	 */
	public void onDataSelected(AdapterView parent, View v, int position, long id);
	
}