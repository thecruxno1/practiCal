package com.tejava.practical;

import android.view.View;
import android.widget.AdapterView;


public interface OnDataSelectionListener {

	/**
	 * �������� ���õǾ��� �� ȣ��Ǵ� �޼ҵ�
	 * 
	 * @param parent Parent View
	 * @param v Target View
	 * @param row Row Index
	 * @param column Column Index
	 * @param id ID for the View
	 */
	public void onDataSelected(AdapterView parent, View v, int position, long id);
	
}