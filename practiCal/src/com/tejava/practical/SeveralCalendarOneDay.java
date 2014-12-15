package com.tejava.practical;

public class SeveralCalendarOneDay {

	private int yearValue;
	private int monthValue;
	private int dayValue;
	private int dayOfWeek;
	private String dayOfWeekString;

	public SeveralCalendarOneDay() {

	}

	public SeveralCalendarOneDay(int day) {
		dayValue = day;
	}

	public SeveralCalendarOneDay(int day, int month, int year) {
		dayValue = day;
		monthValue = month;
		yearValue = year;
	}

	public int getDay() {
		return dayValue;
	}

	public void setDay(int day) {
		this.dayValue = day;
	}

	public int getMonth() {
		return monthValue;
	}

	public void setMonth(int month) {
		this.monthValue = month;
	}

	public int getYear() {
		return yearValue;
	}

	public void setYear(int year) {
		this.yearValue = year;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dow) {
		this.dayOfWeek = dow;
		setDayofWeekString(dayOfWeek);
	}

	public void setDayofWeekString(int dow) {
		if (dow == 0) {
			dayOfWeekString = "SUN";
		} else if (dow == 1) {
			dayOfWeekString = "MON";
		} else if (dow == 2) {
			dayOfWeekString = "TUE";
		} else if (dow == 3) {
			dayOfWeekString = "WED";
		} else if (dow == 4) {
			dayOfWeekString = "THU";
		} else if (dow == 5) {
			dayOfWeekString = "FRI";
		} else if (dow == 6) {
			dayOfWeekString = "SAT";
		}
	}

	public String getDayofWeekString() {
		return dayOfWeekString;
	}
}
