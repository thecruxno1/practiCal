package com.tejava.practical;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	/* variable declaration */

	// five function button
	ImageView btnMonthlyCal;
	ImageView btnSeveralCal;
	ImageView btnDailyCal;
	ImageView btnEventCal;
	ImageView btnOption;

	// Monthly Calendar related variable
	LinearLayout monthlyCalScreen;
	MonthlyCalendarView monthlyCal;
	MonthlyCalendarAdapter monthlyCalAdapter;
	Button monthlyCalPrevious;
	Button monthlyCalNext;
	TextView monthlyCalTop;

	// Several Calendar related variable
	LinearLayout severalCalScreen;

	// Daily calendar related variable
	LinearLayout dailyCalScreen;
	TextView dailyCalTop;

	// Event calendar related variable
	LinearLayout eventCalScreen;

	// option menu related variable
	LinearLayout optionScreen;
	int setting;
	RadioButton month_onclick_option1;
	RadioButton month_onclick_option2;
	RadioButton month_onclick_option3;

	// option menu <- (-_-)?

	// global variable
	Calendar fortoday;
	int selectedDay;
	int selectedMonth;
	int selectedYear;
	int selectedDayOfWeek;

	// test variable
	Button btnTEST;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		variableInitialize();

		monthlyCalInitialize();
		severalCalInitialize();
		dailyCalInitialize();
		eventCalInitizliize();
		optionInitailize();

		listenerInitialize();
	}

	private void variableInitialize() {

		// five function button find
		btnMonthlyCal = (ImageView) findViewById(R.id.btn_monthly);
		btnSeveralCal = (ImageView) findViewById(R.id.btn_several);
		btnDailyCal = (ImageView) findViewById(R.id.btn_daily);
		btnEventCal = (ImageView) findViewById(R.id.btn_eventcal);
		btnOption = (ImageView) findViewById(R.id.btn_option);

		// global variable initialize
		fortoday = Calendar.getInstance();
		selectedDay = fortoday.get(fortoday.DATE);
		selectedMonth = fortoday.get(fortoday.MONTH) + 1;
		selectedYear = fortoday.get(fortoday.YEAR);
		selectedDayOfWeek = fortoday.get(fortoday.DAY_OF_WEEK) - 1;

		// test
		btnTEST = (Button) findViewById(R.id.test_btn1);
	}

	private void listenerInitialize() {
		// five function button's OnClickListener

		btnMonthlyCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthlyCalScreen.setVisibility(View.VISIBLE);
				severalCalScreen.setVisibility(View.INVISIBLE);
				dailyCalScreen.setVisibility(View.INVISIBLE);
				eventCalScreen.setVisibility(View.INVISIBLE);
				optionScreen.setVisibility(View.INVISIBLE);
			}
		});

		btnSeveralCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthlyCalScreen.setVisibility(View.INVISIBLE);
				severalCalScreen.setVisibility(View.VISIBLE);
				dailyCalScreen.setVisibility(View.INVISIBLE);
				eventCalScreen.setVisibility(View.INVISIBLE);
				optionScreen.setVisibility(View.INVISIBLE);

			}
		});

		btnDailyCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dailyCalTop.setText(selectedYear + ". " + selectedMonth + ". "
						+ selectedDay);
				monthlyCalScreen.setVisibility(View.INVISIBLE);
				severalCalScreen.setVisibility(View.INVISIBLE);
				dailyCalScreen.setVisibility(View.VISIBLE);
				eventCalScreen.setVisibility(View.INVISIBLE);
				optionScreen.setVisibility(View.INVISIBLE);
			}
		});

		btnEventCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthlyCalScreen.setVisibility(View.INVISIBLE);
				severalCalScreen.setVisibility(View.INVISIBLE);
				dailyCalScreen.setVisibility(View.INVISIBLE);
				eventCalScreen.setVisibility(View.VISIBLE);
				optionScreen.setVisibility(View.INVISIBLE);
			}
		});

		btnOption.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthlyCalScreen.setVisibility(View.INVISIBLE);
				severalCalScreen.setVisibility(View.INVISIBLE);
				dailyCalScreen.setVisibility(View.INVISIBLE);
				eventCalScreen.setVisibility(View.INVISIBLE);
				optionScreen.setVisibility(View.VISIBLE);
			}
		});
		
		btnTEST.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
				intent.putExtra("mode_setting", 1); // 0: modify, 1: new
				intent.putExtra("db_access_info", "test_value");
				startActivity(intent);				
			}
		});
	}

	private void monthlyCalInitialize() {
		// monthly calendar variable initialize
		monthlyCalScreen = (LinearLayout) findViewById(R.id.monthlycalendar_screen);
		// monthlyCal = (CalendarView) findViewById(R.id.monthlycalendar);
		monthlyCal = (MonthlyCalendarView) findViewById(R.id.monthlycalendar);
		monthlyCalAdapter = new MonthlyCalendarAdapter(this);
		monthlyCal.setAdapter(monthlyCalAdapter);
		monthlyCalPrevious = (Button) findViewById(R.id.monthlycalendar_top_previous);
		monthlyCalNext = (Button) findViewById(R.id.monthlycalendar_top_next);
		monthlyCalTop = (TextView) findViewById(R.id.monthlycalendar_top_date);
		monthlyCalTop.setText(selectedYear + " - " + selectedMonth + " - "
				+ selectedDay + ", " + selectedDayOfWeek);

		// Monthly Calendar listner Init
		monthlyCal.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v,
					int position, long id) {
				MonthlyCalendarOneDay curItem = (MonthlyCalendarOneDay) monthlyCalAdapter
						.getItem(position);

				selectedDay = curItem.getDay();
				selectedMonth = curItem.getMonth();
				selectedYear = curItem.getYear();
				selectedDayOfWeek = curItem.getDayOfWeek();
				monthlyCalTop.setText(selectedYear + " - " + selectedMonth
						+ " - " + selectedDay + ", " + selectedDayOfWeek);

				if (setting == 2) {
					monthlyCalScreen.setVisibility(View.INVISIBLE);
					severalCalScreen.setVisibility(View.VISIBLE);
					dailyCalScreen.setVisibility(View.INVISIBLE);
					eventCalScreen.setVisibility(View.INVISIBLE);
					optionScreen.setVisibility(View.INVISIBLE);
				} else if (setting == 3) {
					monthlyCalScreen.setVisibility(View.INVISIBLE);
					severalCalScreen.setVisibility(View.INVISIBLE);
					dailyCalScreen.setVisibility(View.VISIBLE);
					eventCalScreen.setVisibility(View.INVISIBLE);
					optionScreen.setVisibility(View.INVISIBLE);
				}
			}
		});

		monthlyCalTop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(
						getBaseContext(),
						"Selected Date is\n" + selectedYear + " - "
								+ selectedMonth + " - " + selectedDay + ", "
								+ selectedDayOfWeek,

						Toast.LENGTH_SHORT).show();
			}
		});

		monthlyCalPrevious.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				monthlyCalAdapter.setPreviousMonth();
				monthlyCalAdapter.notifyDataSetChanged();

				int tempmonth = monthlyCalAdapter.curMonth + 1;
				monthlyCalTop.setText(monthlyCalAdapter.curYear + " - "
						+ tempmonth);
			}
		});

		monthlyCalNext.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				monthlyCalAdapter.setNextMonth();
				monthlyCalAdapter.notifyDataSetChanged();

				int tempmonth = monthlyCalAdapter.curMonth + 1;
				monthlyCalTop.setText(monthlyCalAdapter.curYear + " - "
						+ tempmonth);
			}
		});
	}

	private void severalCalInitialize() {
		// // Several Calendar variable Init
		severalCalScreen = (LinearLayout) findViewById(R.id.severaldayscalender_screen);

	}

	private void dailyCalInitialize() {
		// Daily Calendar Init
		dailyCalScreen = (LinearLayout) findViewById(R.id.dailycalendar_screen);
		dailyCalTop = (TextView) findViewById(R.id.dailycalendar_top);
	}

	private void eventCalInitizliize() {
		// Event Calendar Init
		eventCalScreen = (LinearLayout) findViewById(R.id.eventcalendar_screen);
	}

	private void optionInitailize() {
		// Option calendar Init
		optionScreen = (LinearLayout) findViewById(R.id.option_screen);
		month_onclick_option1 = (RadioButton) findViewById(R.id.onclick_monthlycal_noaction);
		month_onclick_option2 = (RadioButton) findViewById(R.id.onclick_monthlycal_several);
		month_onclick_option3 = (RadioButton) findViewById(R.id.onclick_monthlycal_daily);

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
