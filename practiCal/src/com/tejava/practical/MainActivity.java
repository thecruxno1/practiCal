package com.tejava.practical;

import java.io.FileOutputStream;
import java.util.ArrayList;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
	Button startDay;
	Button endDay;
	Button printEventRange;
	TextView upcoming;
	EditText eventNumber;
	Button printEventNumber;
	ListView eventList;
	
	EventCalendar eventCalendar;
	EventListAdapter eventListAdapter;

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
	
	//variables for Test()
	private EventList eventList1 = new EventList(MainActivity.this);
	private EventList eventList2 = new EventList(MainActivity.this);
	private SingleEvent singleEvent = new SingleEvent();
    FileOutputStream fos;

	// test variable
	Button btnTEST;

	@Override
	protected void onCreate(Bundle savedInstanceState) throws RuntimeException
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try
		{
			variableInitialize();
			
			monthlyCalInitialize();
			severalCalInitialize();
			dailyCalInitialize();
			eventCalInitizliize();
			optionInitailize();
			
			listenerInitialize();
		}
		catch(Exception ex)
		{
			
		}
		
//		try
//		{
//			Test();
//		}
//		catch(Exception ex)
//		{
//			
//		}
	}
	
	//test function
	private void Test() throws Exception
	{
		eventList1.Insert(1, 2014, 1, 2, 3, 4, 5, 6, "First", "This is first event");
		eventList1.Insert(2, 2014, 2, 3, 4, 5, 6, 7, "Second", "This is second event");
		eventList1.Insert(3, 2014, 2, 3, 4, 5, 6, 7, "Third", "This is third event");
		eventList1.Insert(4, 2014, 2, 3, 4, 5, 6, 7, "Fourth", "This is fourth event");
		eventList1.Save("test.txt");
		
		eventList2.Load("test.txt");
		eventList2.Delete(3);
		ArrayList<SingleEvent> list = eventList2.Search(2014, 2, 3);
		Toast.makeText(MainActivity.this, list.get(0).GetDescription(), Toast.LENGTH_LONG).show();
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

	private void eventCalInitizliize() throws Exception {
		// Event Calendar variable initialize
		eventCalScreen = (LinearLayout) findViewById(R.id.eventcalendar_screen);
		startDay = (Button) findViewById(R.id.start_day);
		endDay = (Button) findViewById(R.id.end_day);
		printEventRange = (Button) findViewById(R.id.print_event_range);
		upcoming = (TextView) findViewById(R.id.upcoming);
		eventNumber = (EditText) findViewById(R.id.event_number);
		printEventNumber = (Button) findViewById(R.id.print_event_number);
		eventList = (ListView) findViewById(R.id.eventcalendar_eventlist);
		
		eventCalendar = new EventCalendar();
		eventListAdapter = new EventListAdapter(this);
		
		// Event Calendar listener initialize
		printEventNumber.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String eventNumberStr = (eventNumber.getText().toString().equals(""))? null : eventNumber.getText().toString();
				
				if (eventNumberStr == null) {
//					Toast.makeText(MainActivity.this, "empty is null", Toast.LENGTH_LONG).show();
				} else {
					eventCalendar.eventNumber = Integer.parseInt(eventNumberStr);
//					Toast.makeText(MainActivity.this, "event number: " + eventCalendar.eventNumber, Toast.LENGTH_LONG).show();
				}
				
				// save modified data
				// must be changed... use db_access_info

				// db_access_info.start_year = start_year;
				// db_access_info.start_month = start_month;
				// db_access_info.start_day = start_day;
				// db_access_info.start_hour = start_hour;
				// db_access_info.start_min = start_min;
				
				// db_access_info.end_year = end_year;
				// db_access_info.end_month = end_month;
				// db_access_info.end_day = end_day;
				// db_access_info.end_hour = end_hour;
				// db_access_info.end_min = end_min;
				
				// db_access_info.name = ET_event_name.getText().toString();
				// db_access_info.dscrpt = ET_event_description.getText().toString();
				// db_access_info.adm_1 = ET_event_additional_memo_1.getText().toString();
				// db_access_info.adm_2 = ET_event_additional_memo_2.getText().toString();
				// db_access_info.adm_3 = ET_event_additional_memo_3.getText().toString();
				// db_access_info.adm_4 = ET_event_additional_memo_4.getText().toString();
				
			}
		});
		
//		eventList1.Insert(1, 2014, 1, 2, 3, 4, 5, 6, "First", "This is first event");
//		eventList1.Insert(2, 2014, 2, 3, 4, 5, 6, 7, "Second", "This is second event");
//		eventList1.Insert(3, 2014, 2, 3, 4, 5, 6, 7, "Third", "This is third event");
//		eventList1.Insert(4, 2014, 2, 3, 4, 5, 6, 7, "Fourth", "This is fourth event");
//		eventList1.Save("test.txt");
//		
//		eventList2.Load("test.txt");
//		ArrayList<SingleEvent> list = eventList2.Search(2014, 2, 3);
//		
//		for(int i=0; i<list.size(); ++i)
//			eventListAdapter.addItem(list.get(i));
//		
//		eventList.setAdapter(eventListAdapter);;
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
