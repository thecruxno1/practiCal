package com.tejava.practical;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData.Item;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemLongClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
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
	ListView dailyCalEventList;
	
	EventListAdapter dailyCalEventListAdapter;

	// Event calendar related variable
	LinearLayout eventCalScreen;
	Button startDay;
	Button endDay;
	Button printEventRange;
	TextView upcoming;
	EditText eventNumber;
	Button printEventNumber;
	ListView eventCalEventList;
	
	EventCalendar eventCalendar;
	EventListAdapter eventListAdapter;
	
	static final int EVENT_MOD_START_DATE_DIALOG_ID = 200;
	static final int EVENT_MOD_END_DATE_DIALOG_ID = 210;
	
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
	
//	EventList eventList = new EventList(MainActivity.this);
	
//	//variables for Test()
//	private EventList eventList1 = new EventList(MainActivity.this);
//	private EventList eventList2 = new EventList(MainActivity.this);
//	private SingleEvent singleEvent = new SingleEvent();
//    FileOutputStream fos;

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
			
//			Test();
		}
		catch(Exception ex)
		{
			
		}
	}
	
	//test function
	private void Test() throws Exception
	{
//		eventList.Insert( 0, 2014, 1, 2, 3, 4, 5, 6, "First", "This is first event");
//		eventList.Insert( 1, 2014, 2, 3, 4, 5, 6, 7, "Second", "This is second event");
//		eventList.Insert( 2, 2014, 2, 3, 4, 5, 6, 8, "Third", "This is third event");
//		eventList.Insert( 3, 2014, 2, 3, 4, 5, 6, 9, "Fourth", "This is fourth event");
//		eventList.Insert( 4, 2014, 2, 4, 4, 5, 6, 10, "Fifth", "This is fifth event");
//		eventList.Insert( 5, 2014, 2, 5, 4, 5, 6, 11, "Sixth", "This is sixth event");
//		eventList.Insert( 6, 2014, 2, 6, 4, 5, 6, 12, "Seventh", "This is seventh event");
//		eventList.Insert( 7, 2014, 2, 6, 4, 5, 6, 13, "Eighth", "This is eighth event");
//		eventList.Insert( 8, 2014, 2, 7, 4, 5, 6, 14, "Ninth", "This is ninth event");
//		eventList.Insert( 9, 2014, 2, 7, 4, 5, 6, 15, "Tenth", "This is tenth event");
//		eventList.Insert(10, 2014, 3, 7, 4, 5, 6, 16, "Eleventh", "This is eleventh event");
		
		//Toast.makeText(MainActivity.this, "size before delete: " + eventList.GetSize(), Toast.LENGTH_LONG).show();
//		eventList1.Delete(0);
//		Toast.makeText(MainActivity.this, eventList1.Search(2014, 2, 4).get(0).GetId() + " is ID!", Toast.LENGTH_LONG).show();
//		eventList1.Delete(0);
//		Toast.makeText(MainActivity.this, eventList1.Search(2014, 2, 4).get(0).GetId() + " is ID!", Toast.LENGTH_LONG).show();
//		eventList1.Delete(0);
//		Toast.makeText(MainActivity.this, eventList1.Search(2014, 2, 4).get(0).GetId() + " is ID!", Toast.LENGTH_LONG).show();
//		eventList1.Delete(0);
		//Toast.makeText(MainActivity.this, "size before delete: " + eventList.GetSize(), Toast.LENGTH_LONG).show();
		
//		ArrayList<SingleEvent> list = eventList1.Search(2014, 1, 1, 10);
//		Toast.makeText(MainActivity.this, list.get(0).GetStartHour() + ":" + list.get(0).GetStartMin(), Toast.LENGTH_LONG).show();
	}

	private void variableInitialize() throws Exception {

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
		
		// load event list
//		eventList.Insert( 0, 2014, 1, 2, 3, 4, 5, 6, "First", "This is first event");
//		eventList.Insert( 1, 2014, 2, 3, 4, 5, 6, 7, "Second", "This is second event");
//		eventList.Insert( 2, 2014, 2, 3, 4, 5, 6, 8, "Third", "This is third event");
//		eventList.Insert( 3, 2014, 2, 3, 4, 5, 6, 9, "Fourth", "This is fourth event");
//		eventList.Insert( 4, 2014, 2, 4, 4, 5, 6, 10, "Fifth", "This is fifth event");
//		eventList.Insert( 5, 2014, 2, 5, 4, 5, 6, 11, "Sixth", "This is sixth event");
//		eventList.Insert( 6, 2014, 2, 6, 4, 5, 6, 12, "Seventh", "This is seventh event");
//		eventList.Insert( 7, 2014, 2, 6, 4, 5, 6, 13, "Eighth", "This is eighth event");
//		eventList.Insert( 8, 2014, 2, 7, 4, 5, 6, 14, "Ninth", "This is ninth event");
//		eventList.Insert( 9, 2014, 2, 7, 4, 5, 6, 15, "Tenth", "This is tenth event");
//		eventList.Insert(10, 2014, 3, 7, 4, 5, 6, 16, "Eleventh", "This is eleventh event");
//		
//		eventList.Save("save.txt");
//		eventList.Delete(0);
////		eventList.Save("save.txt");
//		eventList.Load("save.txt");
		
		PractiCalEventList.practiCalEventList = new EventList(MainActivity.this);
		
		PractiCalEventList.practiCalEventList.Insert(2014, 1, 2, 3, 4, 5, 6, "First", "This is first event");
		PractiCalEventList.practiCalEventList.Insert(2014, 2, 3, 4, 5, 6, 7, "Second", "This is second event");
		PractiCalEventList.practiCalEventList.Insert(2014, 2, 3, 4, 5, 6, 8, "Third", "This is third event");
		PractiCalEventList.practiCalEventList.Insert(2014, 2, 3, 4, 5, 6, 9, "Fourth", "This is fourth event");
		PractiCalEventList.practiCalEventList.Insert(2014, 2, 4, 4, 5, 6, 10, "Fifth", "This is fifth event");
		PractiCalEventList.practiCalEventList.Insert(2014, 2, 5, 4, 5, 6, 11, "Sixth", "This is sixth event");
		PractiCalEventList.practiCalEventList.Insert(2014, 2, 6, 4, 5, 6, 12, "Seventh", "This is seventh event");
		PractiCalEventList.practiCalEventList.Insert(2014, 2, 6, 4, 5, 6, 13, "Eighth", "This is eighth event");
		PractiCalEventList.practiCalEventList.Insert(2014, 2, 7, 4, 5, 6, 14, "Ninth", "This is ninth event");
		PractiCalEventList.practiCalEventList.Insert(2014, 2, 7, 4, 5, 6, 15, "Tenth", "This is tenth event");
		PractiCalEventList.practiCalEventList.Insert(2014, 3, 7, 4, 5, 6, 16, "Eleventh", "This is eleventh event");
		
		PractiCalEventList.practiCalEventList.Save("save.txt");
		PractiCalEventList.practiCalEventList.Delete(0);
//		PractiCalEventList.practiCalEventList.Save("save.txt");
		PractiCalEventList.practiCalEventList.Load("save.txt");
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
				
				// display event list
				dailyCalEventListAdapter.clear();
				
				ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(selectedYear, selectedMonth, selectedDay);
				if (list.size() == 0) {
					Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_LONG).show();
				} else {
					for (int i = 0; i < list.size(); i++)
					{
//						System.out.println(list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin());
//						Toast.makeText(MainActivity.this, list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin(), Toast.LENGTH_SHORT).show();
						dailyCalEventListAdapter.addItem(list.get(i));
					}
					
					dailyCalEventList.setAdapter(dailyCalEventListAdapter);
				}
					
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
				eventCalendar.startYear = selectedYear;
				eventCalendar.startMonth = selectedMonth;
				eventCalendar.startDay = selectedDay;
				eventCalendar.endYear = selectedYear;
				eventCalendar.endMonth = selectedMonth;
				eventCalendar.endDay = selectedDay;
				
				startDay.setText(eventCalendar.startYear+"/"+eventCalendar.startMonth+"/"+eventCalendar.startDay);
				endDay.setText(eventCalendar.endYear+"/"+eventCalendar.endMonth+"/"+eventCalendar.endDay);
				
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
					dailyCalTop.setText(selectedYear + ". " + selectedMonth + ". "
							+ selectedDay);
					
					// display event list
					dailyCalEventListAdapter.clear();
					
					ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(selectedYear, selectedMonth, selectedDay);
					if (list.size() == 0) {
						Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_LONG).show();
					} else {
						for (int i = 0; i < list.size(); i++)
						{
//							System.out.println(list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin());
//							Toast.makeText(MainActivity.this, list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin(), Toast.LENGTH_SHORT).show();
							dailyCalEventListAdapter.addItem(list.get(i));
						}
						
						dailyCalEventList.setAdapter(dailyCalEventListAdapter);
					}
					
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
		dailyCalEventList = (ListView) findViewById(R.id.dailycalender_eventlist);
		
		dailyCalEventListAdapter = new EventListAdapter(this);
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
		eventCalEventList = (ListView) findViewById(R.id.eventcalendar_eventlist);
		
		eventCalendar = new EventCalendar();
		eventListAdapter = new EventListAdapter(this);
		
		// Event Calendar listener initialize
		printEventNumber.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String eventNumberStr = (eventNumber.getText().toString().equals(""))? null : eventNumber.getText().toString();
				
				if (eventNumberStr != null)
					eventCalendar.eventNumber = Integer.parseInt(eventNumberStr);
				
				eventListAdapter.clear();
				
				int day = fortoday.get(fortoday.DATE);
				int month = fortoday.get(fortoday.MONTH) + 1;
				int year = fortoday.get(fortoday.YEAR);
				
				ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(year, month, day, eventCalendar.eventNumber);
				if (list.size() == 0) {
					eventCalEventList.setAdapter(eventListAdapter);
					Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_LONG).show();
				} else {
					for (int i = 0; i < list.size(); i++)
					{
//						System.out.println(list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin());
//						Toast.makeText(MainActivity.this, list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin(), Toast.LENGTH_SHORT).show();
						eventListAdapter.addItem(list.get(i));
					}
					
					eventCalEventList.setAdapter(eventListAdapter);
					eventCalEventList.setOnItemClickListener(onEventListItemClickListener);
					eventCalEventList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

						@Override
						public boolean onItemLongClick(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							
							printEventNumber.performClick();
							
							return true;
						}
						
					});
				}
			}
		});
		
		printEventRange.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				eventListAdapter.clear();
				
				ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(eventCalendar.startYear, eventCalendar.startMonth, eventCalendar.startDay, 
						eventCalendar.endYear, eventCalendar.endMonth, eventCalendar.endDay);
				if (list.size() == 0) {
					eventCalEventList.setAdapter(eventListAdapter);
					Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_LONG).show();
				} else {
					for (int i = 0; i < list.size(); i++)
					{
//						System.out.println(list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin());
//						Toast.makeText(MainActivity.this, list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin(), Toast.LENGTH_SHORT).show();
						eventListAdapter.addItem(list.get(i));
					}
					
					eventCalEventList.setAdapter(eventListAdapter);
					eventCalEventList.setOnItemClickListener(onEventListItemClickListener);
					eventCalEventList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

						@Override
						public boolean onItemLongClick(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
//							try {
//								PractiCalEventList.practiCalEventList.Insert(11, 2014, 12, 15, 4, 5, 6, 16, "twelveth", "This is twelveth event");
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
							
							Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
							intent.putExtra("mode_setting", 1); // 0: modify, 1: new
//							intent.putExtra("db_access_info", "test_value");
							startActivity(intent);	
							
							printEventRange.performClick();
							
							return true;
						}
						
					});
				}
			}
		});
		
		startDay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(EVENT_MOD_START_DATE_DIALOG_ID);
			}
		});
		
		endDay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(EVENT_MOD_END_DATE_DIALOG_ID);
			}
		});
		
//		eventList1.Save("test.txt");
//		eventList2.Load("test.txt");
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
	
	// for date picker dialog
	private DatePickerDialog.OnDateSetListener mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			eventCalendar.startYear = year;
			eventCalendar.startMonth = monthOfYear+1;
			eventCalendar.startDay = dayOfMonth;
			
//			startDay.setText(eventCalendar.startYear+"/"+(int)(eventCalendar.startMonth+1)+"/"+eventCalendar.startDay);
			startDay.setText(eventCalendar.startYear+"/"+eventCalendar.startMonth+"/"+eventCalendar.startDay);
		}
	};
	private DatePickerDialog.OnDateSetListener mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			eventCalendar.endYear = year;
			eventCalendar.endMonth = monthOfYear+1;
			eventCalendar.endDay = dayOfMonth;
			
//			endDay.setText(eventCalendar.endYear+"/"+(int)(eventCalendar.endMonth+1)+"/"+eventCalendar.endDay);
			endDay.setText(eventCalendar.endYear+"/"+eventCalendar.endMonth+"/"+eventCalendar.endDay);
		}
	};
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case EVENT_MOD_START_DATE_DIALOG_ID:
			return new DatePickerDialog(this, mStartDateSetListener, eventCalendar.startYear, eventCalendar.startMonth, eventCalendar.startDay);
		case EVENT_MOD_END_DATE_DIALOG_ID:
			return new DatePickerDialog(this, mEndDateSetListener, eventCalendar.endYear, eventCalendar.endMonth, eventCalendar.endDay);
		}

		return null;
	}
	
	private OnItemClickListener onEventListItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> adapterView, View clickedView, int pos, long id)
		{
			Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
			intent.putExtra("mode_setting", 0); // 0: modify, 1: new
//			intent.putExtra("db_access_info", "test_value");

			SingleEvent event = (SingleEvent) eventListAdapter.getItem(pos);
			Toast.makeText(MainActivity.this, event.GetName(), Toast.LENGTH_LONG).show();
//			intent.putExtra("Event ID", ((SingleEvent) clickedView).GetId());
//			startActivity(intent);
		}
	};
}
