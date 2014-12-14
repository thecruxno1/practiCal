package com.tejava.practical;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
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

	/* variable decleration */

	// five function button
	ImageView btnMonthlyCal;
	ImageView btnSeveralCal;
	ImageView btnDailyCal;
	ImageView btnEventCal;
	ImageView btnOption;

	// Monthly Calendar related variable
	// CalendarView monthlyCal;
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

	// option menu

	// global variable
	int selectedDay;
	int selectedMonth;
	int selectedYear;
	Calendar fortoday;
	
	//variables for Test()
	private EventList eventList = new EventList(MainActivity.this);
	private EventList eventList2 = new EventList(MainActivity.this);
	private SingleEvent singleEvent = new SingleEvent();
    FileOutputStream fos;
	//////////////////////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState) throws RuntimeException
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		variableInitialize();
		listenerInitialize();
		monthlyCalInitialize();
		severalCalInitialize();
		dailyCalInitialize();
		eventCalInitizliize();
		optionInitailize();
		try
		{
			Test();
		}
		catch(Exception ex)
		{
			
		}
	}
	
	//test function
	private void Test() throws Exception
	{
		eventList.Insert( 1, 2014, 1, 2, 3, 4, 5, 6, "First", "This is first event");
		eventList.Insert( 2, 2014, 2, 3, 4, 5, 6, 7, "Second", "This is second event");
		eventList.Insert( 3, 2014, 2, 3, 4, 5, 6, 8, "Third", "This is third event");
		eventList.Insert( 4, 2014, 2, 3, 4, 5, 6, 9, "Fourth", "This is fourth event");
		eventList.Insert( 5, 2014, 2, 4, 4, 5, 6, 10, "Fifth", "This is fifth event");
		eventList.Insert( 6, 2014, 2, 5, 4, 5, 6, 11, "Sixth", "This is sixth event");
		eventList.Insert( 7, 2014, 2, 6, 4, 5, 6, 12, "Seventh", "This is seventh event");
		eventList.Insert( 8, 2014, 2, 6, 4, 5, 6, 13, "Eighth", "This is eighth event");
		eventList.Insert( 9, 2014, 2, 7, 4, 5, 6, 14, "Ninth", "This is ninth event");
		eventList.Insert(10, 2014, 2, 7, 4, 5, 6, 15, "Tenth", "This is tenth event");
		
		ArrayList<SingleEvent> list = eventList.Search(2014, 2, 7, 7);
		Toast.makeText(MainActivity.this, list.size() + " event searched!", Toast.LENGTH_LONG).show();
	}
	//////////////////////////////////

	private void variableInitialize() {

		// five function button find
		btnMonthlyCal = (ImageView) findViewById(R.id.btn_monthly);
		btnSeveralCal = (ImageView) findViewById(R.id.btn_several);
		btnDailyCal = (ImageView) findViewById(R.id.btn_daily);
		btnEventCal = (ImageView) findViewById(R.id.btn_eventcal);
		btnOption = (ImageView) findViewById(R.id.btn_option);

		// global variable init
		fortoday = Calendar.getInstance();
		selectedDay = fortoday.get(fortoday.DATE);
		selectedMonth = fortoday.get(fortoday.MONTH) + 1;
		selectedYear = fortoday.get(fortoday.YEAR);
	}

	private void monthlyCalInitialize() {
		// Monthly Calendar valiable Init
		monthlyCalScreen = (LinearLayout) findViewById(R.id.monthlycalendar_screen);
		// monthlyCal = (CalendarView) findViewById(R.id.monthlycalendar);
		monthlyCal = (MonthlyCalendarView) findViewById(R.id.monthlycalendar);
		monthlyCalAdapter = new MonthlyCalendarAdapter(this);
		monthlyCal.setAdapter(monthlyCalAdapter);
		monthlyCalPrevious = (Button) findViewById(R.id.monthlycalendar_top_previous);
		monthlyCalNext = (Button) findViewById(R.id.monthlycalendar_top_next);
		monthlyCalTop = (TextView) findViewById(R.id.monthlycalendar_top_date);
		monthlyCalTop.setText(selectedYear + " - " + selectedMonth + " - "
				+ selectedDay);

		// Monthly Calendar listner Init
		monthlyCal.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v,
					int position, long id) {
				MonthlyCalendarOneDay curItem = (MonthlyCalendarOneDay) monthlyCalAdapter
						.getItem(position);

				selectedDay = curItem.getDay();
				selectedMonth = 1 + monthlyCalAdapter.curMonth;
				selectedYear = monthlyCalAdapter.curYear;
				monthlyCalTop.setText(selectedYear + " - " + selectedMonth
						+ " - " + selectedDay);

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
								+ selectedMonth + " - " + selectedDay,

						Toast.LENGTH_SHORT).show();
			}
		});

		monthlyCalPrevious.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				monthlyCalAdapter.setPreviousMonth();
				monthlyCalAdapter.notifyDataSetChanged();
				
				int tempmonth = monthlyCalAdapter.curMonth+1;
				monthlyCalTop.setText(monthlyCalAdapter.curYear + " - " + tempmonth);
			}
		});

		monthlyCalNext.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				monthlyCalAdapter.setNextMonth();
				monthlyCalAdapter.notifyDataSetChanged();
				
				int tempmonth = monthlyCalAdapter.curMonth+1;
				monthlyCalTop.setText(monthlyCalAdapter.curYear + " - " + tempmonth);
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

	private void listenerInitialize() {
		// five function button onclicklistener

		btnMonthlyCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				monthlyCalScreen.setVisibility(View.VISIBLE);
				severalCalScreen.setVisibility(View.INVISIBLE);
				dailyCalScreen.setVisibility(View.INVISIBLE);
				eventCalScreen.setVisibility(View.INVISIBLE);
				optionScreen.setVisibility(View.INVISIBLE);
				// printMonthlyCal();
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
				// printSeveralCal();

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
				// printDailyCal();
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
				// printEventCal();
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
				// printOption();
			}
		});

	}
}
