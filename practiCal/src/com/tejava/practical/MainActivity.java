package com.tejava.practical;

import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	/* variable decleration */

	// five function button
	ImageView btnMonthlyCal;
	ImageView btnSeveralCal;
	ImageView btnDailyCal;
	ImageView btnEventCal;
	ImageView btnOption;

	// calendar view
	CalendarView monthlyCal;
	LinearLayout severalCal;
	LinearLayout dailyCal;
	LinearLayout eventCal;
	LinearLayout optionmenu;

	// daily calendar
	TextView dailyCalTop;

	// option menu
	int setting;
	RadioButton month_onclick_option1;
	RadioButton month_onclick_option2;
	RadioButton month_onclick_option3;

	// global variable
	int selectedDay;
	int selectedMonth;
	int selectedYear;
	Calendar fortoday;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		variableInitialize();
		listenerInitialize();
	}

	private void variableInitialize() {

		// five function button find
		btnMonthlyCal = (ImageView) findViewById(R.id.btn_monthly);
		btnSeveralCal = (ImageView) findViewById(R.id.btn_several);
		btnDailyCal = (ImageView) findViewById(R.id.btn_daily);
		btnEventCal = (ImageView) findViewById(R.id.btn_eventcal);
		btnOption = (ImageView) findViewById(R.id.btn_option);

		// calendar view find
		monthlyCal = (CalendarView) findViewById(R.id.monthlycalendar);
		severalCal = (LinearLayout) findViewById(R.id.severaldayscalender_screen);
		dailyCal = (LinearLayout) findViewById(R.id.dailycalendar_screen);
		eventCal = (LinearLayout) findViewById(R.id.eventcalendar_screen);
		optionmenu = (LinearLayout) findViewById(R.id.option_screen);
		
		// daily calendar find
		dailyCalTop = (TextView) findViewById(R.id.dailycalendar_top);

		// option menu find
		month_onclick_option1 = (RadioButton) findViewById(R.id.onclick_monthlycal_noaction);
		month_onclick_option2 = (RadioButton) findViewById(R.id.onclick_monthlycal_several);
		month_onclick_option3 = (RadioButton) findViewById(R.id.onclick_monthlycal_daily);

		// global variable init
		fortoday = Calendar.getInstance();
		selectedDay = fortoday.get(fortoday.DATE);
		selectedMonth = fortoday.get(fortoday.MONTH) + 1;
		selectedYear = fortoday.get(fortoday.YEAR);
	}

	private void listenerInitialize() {
		// five function button onclicklistener

		btnMonthlyCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthlyCal.setVisibility(View.VISIBLE);
				severalCal.setVisibility(View.INVISIBLE);
				dailyCal.setVisibility(View.INVISIBLE);
				eventCal.setVisibility(View.INVISIBLE);
				optionmenu.setVisibility(View.INVISIBLE);
				// printMonthlyCal();
			}
		});

		btnSeveralCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthlyCal.setVisibility(View.INVISIBLE);
				severalCal.setVisibility(View.VISIBLE);
				dailyCal.setVisibility(View.INVISIBLE);
				eventCal.setVisibility(View.INVISIBLE);
				optionmenu.setVisibility(View.INVISIBLE);
				// printSeveralCal();

			}
		});

		btnDailyCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dailyCalTop.setText(selectedYear + ". " + selectedMonth + ". "
						+ selectedDay);
				monthlyCal.setVisibility(View.INVISIBLE);
				severalCal.setVisibility(View.INVISIBLE);
				dailyCal.setVisibility(View.VISIBLE);
				eventCal.setVisibility(View.INVISIBLE);
				optionmenu.setVisibility(View.INVISIBLE);

				// printDailyCal();

			}
		});

		btnEventCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthlyCal.setVisibility(View.INVISIBLE);
				severalCal.setVisibility(View.INVISIBLE);
				dailyCal.setVisibility(View.INVISIBLE);
				eventCal.setVisibility(View.VISIBLE);
				optionmenu.setVisibility(View.INVISIBLE);
				// printEventCal();
			}
		});

		btnOption.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthlyCal.setVisibility(View.INVISIBLE);
				severalCal.setVisibility(View.INVISIBLE);
				dailyCal.setVisibility(View.INVISIBLE);
				eventCal.setVisibility(View.INVISIBLE);
				optionmenu.setVisibility(View.VISIBLE);
				// printOption();
			}
		});

		// monthly calendar
		monthlyCal.setOnDateChangeListener(new OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				selectedDay = dayOfMonth;
				selectedMonth = month + 1;
				selectedYear = year;
				dailyCalTop.setText(selectedYear + ". " + selectedMonth + ". "
						+ selectedDay);
				
				if(setting==2){
					monthlyCal.setVisibility(View.INVISIBLE);
					severalCal.setVisibility(View.VISIBLE);
					dailyCal.setVisibility(View.INVISIBLE);
					eventCal.setVisibility(View.INVISIBLE);
					optionmenu.setVisibility(View.INVISIBLE);
				}
				else if(setting==3)
				{
					monthlyCal.setVisibility(View.INVISIBLE);
					severalCal.setVisibility(View.INVISIBLE);
					dailyCal.setVisibility(View.VISIBLE);
					eventCal.setVisibility(View.INVISIBLE);
					optionmenu.setVisibility(View.INVISIBLE);
				}
			}
		});

		// Option menu
		month_onclick_option1
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							setting = 1;
					}
				});
		month_onclick_option2
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							setting = 2;
					}
				});
		month_onclick_option3
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							setting = 3;
					}
				});
	}
}
