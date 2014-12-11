package com.tejava.practical;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	// function button init
	ImageView btnMonthlyCal;
	ImageView btnSeveralCal;
	ImageView btnDailyCal;
	ImageView btnEventCal;
	ImageView btnOption;

	// calendar view init
	CalendarView monthlyCal;
	LinearLayout dailyCal;

	TextView dailyCalTop;

	int selectedDay;
	int selectedMonth;
	int selectedYear;

	Calendar fortoday = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnMonthlyCal = (ImageView) findViewById(R.id.btn_monthly);
		btnSeveralCal = (ImageView) findViewById(R.id.btn_several);
		btnDailyCal = (ImageView) findViewById(R.id.btn_daily);
		btnEventCal = (ImageView) findViewById(R.id.btn_eventcal);
		btnOption = (ImageView) findViewById(R.id.btn_option);

		monthlyCal = (CalendarView) findViewById(R.id.monthlycalendar);
		dailyCal = (LinearLayout) findViewById(R.id.dailycalendar_screen);

		dailyCalTop = (TextView) findViewById(R.id.dailycalendar_top);

		selectedDay = fortoday.get(fortoday.DATE);
		selectedMonth = fortoday.get(fortoday.MONTH) + 1;
		selectedYear = fortoday.get(fortoday.YEAR);

		// selection
		monthlyCal.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				selectedDay = dayOfMonth;
				selectedMonth = month + 1;
				selectedYear = year;

				dailyCalTop.setText(selectedYear + ". " + selectedMonth + ". "
						+ selectedDay);
				monthlyCal.setVisibility(view.INVISIBLE);
				dailyCal.setVisibility(view.VISIBLE);
			}
		});

		// make onclicklistener
		btnMonthlyCal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				monthlyCal.setVisibility(View.VISIBLE);
				dailyCal.setVisibility(View.INVISIBLE);
				// printMonthlyCal();
			}
		});

		btnSeveralCal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// printSeveralCal();

			}
		});

		btnDailyCal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dailyCalTop.setText(selectedYear + ". " + selectedMonth + ". "
						+ selectedDay);
				monthlyCal.setVisibility(View.INVISIBLE);
				dailyCal.setVisibility(View.VISIBLE);

				// printDailyCal();

			}
		});

		btnEventCal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// printEventCal();

			}
		});

		btnOption.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// printOption();

			}
		});

	}

}
