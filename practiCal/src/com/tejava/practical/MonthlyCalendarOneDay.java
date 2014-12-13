package com.tejava.practical;

import java.util.Vector;

public class MonthlyCalendarOneDay {

	private int yearValue;
	private int monthValue;
	private int dayValue;
	//public Vector<String> singleEventList;
	
	public MonthlyCalendarOneDay() {

	}

	public MonthlyCalendarOneDay(int day) {
		dayValue = day;
		//singleEventList = new Vector<String>();
	}

	public MonthlyCalendarOneDay(int day, int month, int year) {
		dayValue = day;
		monthValue = month;
		yearValue = year;
		//singleEventList = new Vector<String>();
	}
	
	public int getDay() {
		return dayValue;
	}

	public void setDay(int day) {
		this.dayValue = day;
	}
	
	public int getMonth()
	{
		return monthValue;
	}
	
	public void setMonth(int month){
		this.monthValue = month;
	}
	
	public int getYear(){
		return yearValue;
	}
	
	public void setYear(int year){
		this.yearValue = year;
	}
}
