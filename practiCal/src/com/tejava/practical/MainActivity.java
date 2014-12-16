package com.tejava.practical;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
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

public class MainActivity extends Activity {

	/* variable declaration */

	// this eventList stores whole events of this calendar
	EventList eventList = new EventList(MainActivity.this);

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
	TextView severalCalTop;
	LinearLayout severalCalScreen;
	SeveralCalendarView severalCal;
	SeveralCalendarAdapter severalCalAdapter;
	ListView severalCalEventList;
	SeveralCalendarEventListAdapter severalCalEventListAdapter;
	Button severalCalAdd;

	// Daily calendar related variable
	LinearLayout dailyCalScreen;
	TextView dailyCalTop;
	ListView dailyCalEventList;

	Button dailyCalAdd;
	
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

	Button eventCalAdd;
	
	EventCalendar eventCalendar;
	EventListAdapter eventListAdapter;
	int flag;
	
	static final int EVENT_MOD_START_DATE_DIALOG_ID = 200;
	static final int EVENT_MOD_END_DATE_DIALOG_ID = 210;

	static final int SEVERAL_CAL_EVENT_LIST = 30;
	static final int DAILY_CAL_EVENT_LIST = 31;
	static final int EVENT_CAL_EVENT_LIST = 32;
	
	static final int PRINT_EVENT_RANGE = 0;
	static final int PRINT_EVENT_NUMBER = 1;
	static final int NONE = 2;
	
	static final int MODIFY = 99;
	
	// option menu related variable
	LinearLayout optionScreen;
	int mouth_onclick_setting;
	int several_days_setting;
	RadioButton month_onclick_option1;
	RadioButton month_onclick_option2;
	RadioButton month_onclick_option3;
	RadioButton several_days_option1;
	RadioButton several_days_option2;
	RadioButton several_days_option3;

	// option menu <- (-_-)?

	// global variable
	Calendar fortoday;
	int selectedDay;
	int selectedMonth;
	int selectedYear;
	String selectedDayOfWeek;

	@Override
	protected void onCreate(Bundle savedInstanceState) throws RuntimeException {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			variableInitialize();
			optionInitailize();
			
			monthlyCalInitialize();
			severalCalInitialize();
			dailyCalInitialize();
			eventCalInitizliize();
			

			listenerInitialize();

			// Test();
		} catch (Exception ex) {

		}
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
		selectedDayOfWeek = convertDaytoString(fortoday
				.get(fortoday.DAY_OF_WEEK) - 1);

		mouth_onclick_setting = 1;
		several_days_setting = 7;
		
		
		PractiCalEventList.practiCalEventList = new EventList(MainActivity.this);
		
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
				severalCalTop.setText(selectedYear + " - " + selectedMonth);
				severalCalAdapter.recalculate(selectedYear, selectedMonth - 1, selectedDay);
				severalCal.setAdapter(severalCalAdapter);
				severalCalAdapter.notifyDataSetChanged();

				loadSeveralEvent();

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

				dailyCalTop.setText(selectedYear + ". " + selectedMonth + ". "+ selectedDay);
				loadDailyEvent();
				
//				// display event list
//				dailyCalEventListAdapter.clear();
//				
//				ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(selectedYear, selectedMonth, selectedDay);
//				if (list.size() == 0) {
//					Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_SHORT).show();
//				} else {
//					for (int i = 0; i < list.size(); i++)
//					{
////						System.out.println(list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin());
////						Toast.makeText(MainActivity.this, list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin(), Toast.LENGTH_SHORT).show();
//						dailyCalEventListAdapter.addItem(list.get(i));
//					}
//					
//					dailyCalEventList.setAdapter(dailyCalEventListAdapter);
//					dailyCalEventList.setOnItemClickListener(onEventListItemClickListener);
//				}
					
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

				startDay.setText(eventCalendar.startYear + "/"
						+ eventCalendar.startMonth + "/"
						+ eventCalendar.startDay);
				endDay.setText(eventCalendar.endYear + "/"
						+ eventCalendar.endMonth + "/" + eventCalendar.endDay);
				
				// clear event list
				eventListAdapter.clear();

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
	}


	private void monthlyCalInitialize() {
		// monthly calendar variable initialize

		monthlyCalScreen = (LinearLayout) findViewById(R.id.monthlycalendar_screen);
		monthlyCal = (MonthlyCalendarView) findViewById(R.id.monthlycalendar);

		monthlyCalAdapter = new MonthlyCalendarAdapter(this);
		monthlyCalAdapter.setEventlist(PractiCalEventList.practiCalEventList);
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

				if (curItem.getDay() != 0) {
					selectedDay = curItem.getDay();
					selectedMonth = curItem.getMonth();
					selectedYear = curItem.getYear();
					selectedDayOfWeek = curItem.getDayofWeekString();

					monthlyCalTop.setText(selectedYear + " - " + selectedMonth
							+ " - " + selectedDay + ", " + selectedDayOfWeek);

					if (mouth_onclick_setting == 2) {
						severalCalTop.setText(selectedYear + " - "
								+ selectedMonth);
						severalCal.setAdapter(severalCalAdapter);
						severalCalAdapter.recalculate(selectedYear,
								selectedMonth - 1, selectedDay);
						severalCalAdapter.notifyDataSetChanged();

						loadSeveralEvent();

						monthlyCalScreen.setVisibility(View.INVISIBLE);
						severalCalScreen.setVisibility(View.VISIBLE);
						dailyCalScreen.setVisibility(View.INVISIBLE);
						eventCalScreen.setVisibility(View.INVISIBLE);
						optionScreen.setVisibility(View.INVISIBLE);
					} else if (mouth_onclick_setting == 3) {
						dailyCalTop.setText(selectedYear + " - "
								+ selectedMonth + " - " + selectedDay + ", "
								+ selectedDayOfWeek);

						loadDailyEvent();

						monthlyCalScreen.setVisibility(View.INVISIBLE);
						severalCalScreen.setVisibility(View.INVISIBLE);
						dailyCalScreen.setVisibility(View.VISIBLE);
						eventCalScreen.setVisibility(View.INVISIBLE);
						optionScreen.setVisibility(View.INVISIBLE);
					}
				}
			}
		});

		monthlyCalTop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * Toast.makeText( getBaseContext(), "Selected Date is\n" +
				 * selectedYear + " - " + selectedMonth + " - " + selectedDay +
				 * ", " + selectedDayOfWeek,
				 * 
				 * Toast.LENGTH_SHORT).show();
				 */
				DatePickerDialog mDatePicker = new DatePickerDialog(
						MainActivity.this, mMonthlyCalDateSetListener,
						selectedYear, selectedMonth - 1, selectedDay);
				mDatePicker.show();
			}
		});

		monthlyCalPrevious.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				monthlyCalAdapter.setPreviousMonth();
				monthlyCalAdapter.setEventlist(PractiCalEventList.practiCalEventList);
				monthlyCalAdapter.notifyDataSetChanged();

				monthlyCalTop.setText(monthlyCalAdapter.curYear + " - "
						+ (int) (monthlyCalAdapter.curMonth + 1));
			}
		});

		monthlyCalNext.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				monthlyCalAdapter.setNextMonth();
				monthlyCalAdapter.setEventlist(PractiCalEventList.practiCalEventList);
				monthlyCalAdapter.notifyDataSetChanged();

				monthlyCalTop.setText(monthlyCalAdapter.curYear + " - "
						+ (int) (monthlyCalAdapter.curMonth + 1));
			}
		});
	}

	private void severalCalInitialize() {
		// // Several Calendar variable Init
		severalCalScreen = (LinearLayout) findViewById(R.id.severalcalendar_screen);
		severalCal = (SeveralCalendarView) findViewById(R.id.severalcalendar);
		severalCal.setSeveralNumber(several_days_setting);
		
		severalCalAdapter = new SeveralCalendarAdapter(this);
		severalCalAdapter.setSeveralNumber(several_days_setting);
		severalCalAdapter.setEventList(PractiCalEventList.practiCalEventList);
		severalCal.setAdapter(severalCalAdapter);
		
		severalCalAdd = (Button) findViewById(R.id.several_add);
		severalCalAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
				intent.putExtra("mode_setting", 1); // 0: modify, 1: new
				intent.putExtra("add_from", SEVERAL_CAL_EVENT_LIST);
				
				startActivityForResult(intent, 0);
			}
		});

		severalCalEventList = (ListView) findViewById(R.id.severalcalendar_eventlist);
		severalCalEventListAdapter = new SeveralCalendarEventListAdapter(this);

		severalCalTop = (TextView) findViewById(R.id.severalcalendar_month);
		severalCalTop.setText(selectedYear + " - " + selectedMonth);

		severalCal.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v,
					int position, long id) {
				SeveralCalendarOneDay curItem = (SeveralCalendarOneDay) severalCalAdapter
						.getItem(position);

				if (curItem.getDay() != 0) {
					selectedDay = curItem.getDay();
					selectedMonth = curItem.getMonth();
					selectedYear = curItem.getYear();
					selectedDayOfWeek = curItem.getDayofWeekString();

					severalCalTop.setText(selectedYear + " - " + selectedMonth
							+ " - " + selectedDay + ", " + selectedDayOfWeek);

					loadSeveralEvent();
				}
			}
		});
	}

	private void dailyCalInitialize() {
		// Daily Calendar Init
		dailyCalScreen = (LinearLayout) findViewById(R.id.dailycalendar_screen);
		dailyCalTop = (TextView) findViewById(R.id.dailycalendar_top);
		dailyCalEventList = (ListView) findViewById(R.id.dailycalender_eventlist);
		dailyCalAdd = (Button) findViewById(R.id.daily_add);
		
		dailyCalEventListAdapter = new EventListAdapter(this);
		
		dailyCalAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
				intent.putExtra("mode_setting", 1); // 0: modify, 1: new
				intent.putExtra("add_from", DAILY_CAL_EVENT_LIST);
				
				startActivityForResult(intent, 0);
			}
		});
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

		eventCalAdd = (Button) findViewById(R.id.event_add);
		
		eventCalendar = new EventCalendar();
		eventListAdapter = new EventListAdapter(this);

		flag = NONE;
		
		// Event Calendar listener initialize
		eventCalAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
				intent.putExtra("mode_setting", 1); // 0: modify, 1: new
				intent.putExtra("add_from", EVENT_CAL_EVENT_LIST);
				
				startActivityForResult(intent, 0);
			}
		});
		
		printEventRange.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadEventRange();
				
				// hide keyboard
				InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				mInputMethodManager.hideSoftInputFromWindow(eventNumber.getWindowToken(), 0);
				
//				flag = PRINT_EVENT_RANGE;
//				
//				eventListAdapter.clear();
//				
//				ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(eventCalendar.startYear, eventCalendar.startMonth, eventCalendar.startDay, 
//						eventCalendar.endYear, eventCalendar.endMonth, eventCalendar.endDay);
//				if (list.size() == 0) {
//					eventCalEventList.setAdapter(eventListAdapter);
//					Toast.makeText(MainActivity.this, "No events",
//							Toast.LENGTH_SHORT).show();
//				} else {
//					for (int i = 0; i < list.size(); i++) {
//						// System.out.println(list.get(i).GetStartHour() + ":" +
//						// list.get(i).GetStartMin());
//						// Toast.makeText(MainActivity.this,
//						// list.get(i).GetStartHour() + ":" +
//						// list.get(i).GetStartMin(),
//						// Toast.LENGTH_SHORT).show();
//						eventListAdapter.addItem(list.get(i));
//					}
//
//					eventCalEventList.setAdapter(eventListAdapter);
//					eventCalEventList.setOnItemClickListener(onEventListItemClickListener);
////					eventCalEventList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
////
////						@Override
////						public boolean onItemLongClick(AdapterView<?> arg0,
////								View arg1, int arg2, long arg3) {
////							Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
////							intent.putExtra("mode_setting", 1); // 0: modify, 1: new
////							intent.putExtra("db_access_info", "test_value");
////							intent.putExtra("add_from", PRINT_EVENT_RANGE);
////							
////							startActivityForResult(intent, 0);
////							return true;
////						}
////						
////					});
//				}
			}
		});
		
		printEventNumber.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadEventNumber();
				
				// hide keyboard
				InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				mInputMethodManager.hideSoftInputFromWindow(eventNumber.getWindowToken(), 0);
				
//				flag = PRINT_EVENT_NUMBER;
//				
//				String eventNumberStr = (eventNumber.getText().toString().equals(""))? null : eventNumber.getText().toString();
//				
//				if (eventNumberStr != null)
//					eventCalendar.eventNumber = Integer
//							.parseInt(eventNumberStr);
//
//				eventListAdapter.clear();
//
//				int day = fortoday.get(fortoday.DATE);
//				int month = fortoday.get(fortoday.MONTH) + 1;
//				int year = fortoday.get(fortoday.YEAR);
//				
//				ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(year, month, day, eventCalendar.eventNumber);
//				if (list.size() == 0) {
//					eventCalEventList.setAdapter(eventListAdapter);
//					Toast.makeText(MainActivity.this, "No events",
//							Toast.LENGTH_SHORT).show();
//				} else {
//					for (int i = 0; i < list.size(); i++) {
//						// System.out.println(list.get(i).GetStartHour() + ":" +
//						// list.get(i).GetStartMin());
//						// Toast.makeText(MainActivity.this,
//						// list.get(i).GetStartHour() + ":" +
//						// list.get(i).GetStartMin(),
//						// Toast.LENGTH_SHORT).show();
//						eventListAdapter.addItem(list.get(i));
//					}
//
//					eventCalEventList.setAdapter(eventListAdapter);
//					eventCalEventList.setOnItemClickListener(onEventListItemClickListener);
////					eventCalEventList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
////
////						@Override
////						public boolean onItemLongClick(AdapterView<?> arg0,
////								View arg1, int arg2, long arg3) {
////							Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
////							intent.putExtra("mode_setting", 1); // 0: modify, 1: new
////							intent.putExtra("db_access_info", "test_value");
////							intent.putExtra("add_from", PRINT_EVENT_NUMBER);
////							
////							startActivityForResult(intent, 0);
////							return true;
////						}
////						
////					});
//				}
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

		// eventList1.Save("test.txt");
		// eventList2.Load("test.txt");
	}

	private void optionInitailize() {
		// Option calendar Init
		optionScreen = (LinearLayout) findViewById(R.id.option_screen);
		month_onclick_option1 = (RadioButton) findViewById(R.id.onclick_monthlycal_noaction);
		month_onclick_option2 = (RadioButton) findViewById(R.id.onclick_monthlycal_several);
		month_onclick_option3 = (RadioButton) findViewById(R.id.onclick_monthlycal_daily);
		month_onclick_option1.setChecked(true);

		several_days_option1 = (RadioButton) findViewById(R.id.several_three);
		several_days_option2 = (RadioButton) findViewById(R.id.several_five);
		several_days_option3 = (RadioButton) findViewById(R.id.several_seven);
		several_days_option3.setChecked(true);

		// Option menu
		month_onclick_option1
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							mouth_onclick_setting = 1;
					}
				});
		month_onclick_option2
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							mouth_onclick_setting = 2;
					}
				});
		month_onclick_option3
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							mouth_onclick_setting = 3;
					}
				});
		several_days_option1
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							several_days_setting = 3;
						severalCal.setSeveralNumber(several_days_setting);
						severalCal.init(MainActivity.this);
						severalCalAdapter.setSeveralNumber(several_days_setting);
					}
				});
		several_days_option2
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							several_days_setting = 5;
						severalCal.setSeveralNumber(several_days_setting);
						severalCal.init(MainActivity.this);
						severalCalAdapter.setSeveralNumber(several_days_setting);
					}
				});
		several_days_option3
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked)
							several_days_setting = 7;
						severalCal.setSeveralNumber(several_days_setting);
						severalCal.init(MainActivity.this);
						severalCalAdapter.setSeveralNumber(several_days_setting);
					}
				});
	}

	// for date picker dialog
	private DatePickerDialog.OnDateSetListener mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			eventCalendar.startYear = year;
			eventCalendar.startMonth = monthOfYear + 1;
			eventCalendar.startDay = dayOfMonth;

			// startDay.setText(eventCalendar.startYear+"/"+(int)(eventCalendar.startMonth+1)+"/"+eventCalendar.startDay);
			startDay.setText(eventCalendar.startYear + "/"
					+ eventCalendar.startMonth + "/" + eventCalendar.startDay);
		}
	};
	private DatePickerDialog.OnDateSetListener mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			eventCalendar.endYear = year;
			eventCalendar.endMonth = monthOfYear + 1;
			eventCalendar.endDay = dayOfMonth;

			// endDay.setText(eventCalendar.endYear+"/"+(int)(eventCalendar.endMonth+1)+"/"+eventCalendar.endDay);
			endDay.setText(eventCalendar.endYear + "/" + eventCalendar.endMonth
					+ "/" + eventCalendar.endDay);
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case EVENT_MOD_START_DATE_DIALOG_ID:
			return new DatePickerDialog(this, mStartDateSetListener,
					eventCalendar.startYear, eventCalendar.startMonth,
					eventCalendar.startDay);
		case EVENT_MOD_END_DATE_DIALOG_ID:
			return new DatePickerDialog(this, mEndDateSetListener,
					eventCalendar.endYear, eventCalendar.endMonth,
					eventCalendar.endDay);
		}

		return null;
	}

	private OnItemClickListener onEventListItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> adapterView, View clickedView,
				int pos, long id) {
			Intent intent = new Intent(MainActivity.this,
					EventmodActivity.class);
			intent.putExtra("mode_setting", 0); // 0: modify, 1: new

			SingleEvent event = (SingleEvent) adapterView.getItemAtPosition(pos);
			intent.putExtra("event_obj", event);
			
			startActivityForResult(intent, 0);
		}
	};
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (resultCode) {
		case SEVERAL_CAL_EVENT_LIST:
//			btnSeveralCal.performClick();
			loadSeveralEvent();
			break;
			
		case DAILY_CAL_EVENT_LIST:
//			btnDailyCal.performClick();
			loadDailyEvent();
			break;
			
		case EVENT_CAL_EVENT_LIST:
		{
			switch (flag) {
			case PRINT_EVENT_RANGE:
				printEventRange.performClick();
				break;
				
			case PRINT_EVENT_NUMBER:
				printEventNumber.performClick();
				break;

			default:
				break;
			}
		}
			break;
			
		case MODIFY:
		{
			// daily calendar refresh
			dailyCalTop.setText(selectedYear + ". " + selectedMonth + ". "+ selectedDay);
			
			// display event list
			dailyCalEventListAdapter.clear();
			
			ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(selectedYear, selectedMonth, selectedDay);
			if (list.size() == 0) {
//				Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_SHORT).show();
			} else {
				for (int i = 0; i < list.size(); i++)
				{
//					System.out.println(list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin());
//					Toast.makeText(MainActivity.this, list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin(), Toast.LENGTH_SHORT).show();
					dailyCalEventListAdapter.addItem(list.get(i));
				}
				
				dailyCalEventList.setAdapter(dailyCalEventListAdapter);
				dailyCalEventList.setOnItemClickListener(onEventListItemClickListener);
			}
			
			switch (flag) {
			// print event range refresh
			case PRINT_EVENT_RANGE:
			{
				flag = PRINT_EVENT_RANGE;
				
				eventListAdapter.clear();
				
				ArrayList<SingleEvent> list1 = PractiCalEventList.practiCalEventList.Search(eventCalendar.startYear, eventCalendar.startMonth, eventCalendar.startDay, 
						eventCalendar.endYear, eventCalendar.endMonth, eventCalendar.endDay);
				if (list1.size() == 0) {
					eventCalEventList.setAdapter(eventListAdapter);
//					Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_SHORT).show();
				} else {
					for (int i = 0; i < list1.size(); i++)
					{
//						System.out.println(list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin());
//						Toast.makeText(MainActivity.this, list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin(), Toast.LENGTH_SHORT).show();
						eventListAdapter.addItem(list1.get(i));
					}
					
					eventCalEventList.setAdapter(eventListAdapter);
					eventCalEventList.setOnItemClickListener(onEventListItemClickListener);
//					eventCalEventList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//						@Override
//						public boolean onItemLongClick(AdapterView<?> arg0,
//								View arg1, int arg2, long arg3) {
//							Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
//							intent.putExtra("mode_setting", 1); // 0: modify, 1: new
//							intent.putExtra("db_access_info", "test_value");
//							intent.putExtra("add_from", PRINT_EVENT_RANGE);
//							
//							startActivityForResult(intent, 0);
//							return true;
//						}
//						
//					});
				}
				
			}
				break;
				
			case PRINT_EVENT_NUMBER:
			{
				flag = PRINT_EVENT_NUMBER;
				
				String eventNumberStr = (eventNumber.getText().toString().equals(""))? null : eventNumber.getText().toString();
				
				if (eventNumberStr != null)
					eventCalendar.eventNumber = Integer.parseInt(eventNumberStr);
				
				eventListAdapter.clear();
				
				int day = fortoday.get(fortoday.DATE);
				int month = fortoday.get(fortoday.MONTH) + 1;
				int year = fortoday.get(fortoday.YEAR);
				
				ArrayList<SingleEvent> list1 = PractiCalEventList.practiCalEventList.Search(year, month, day, eventCalendar.eventNumber);
				if (list1.size() == 0) {
					eventCalEventList.setAdapter(eventListAdapter);
//					Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_SHORT).show();
				} else {
					for (int i = 0; i < list1.size(); i++)
					{
//						System.out.println(list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin());
//						Toast.makeText(MainActivity.this, list.get(i).GetStartHour() + ":" + list.get(i).GetStartMin(), Toast.LENGTH_SHORT).show();
						eventListAdapter.addItem(list1.get(i));
					}
					
					eventCalEventList.setAdapter(eventListAdapter);
					eventCalEventList.setOnItemClickListener(onEventListItemClickListener);
//					eventCalEventList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//						@Override
//						public boolean onItemLongClick(AdapterView<?> arg0,
//								View arg1, int arg2, long arg3) {
//							Intent intent = new Intent(MainActivity.this, EventmodActivity.class);
//							intent.putExtra("mode_setting", 1); // 0: modify, 1: new
//							intent.putExtra("db_access_info", "test_value");
//							intent.putExtra("add_from", PRINT_EVENT_NUMBER);
//							
//							startActivityForResult(intent, 0);
//							return true;
//						}
//						
//					});
				}
			}
				break;

			default:
				break;
			}
		}
			break;

		default:
			break;
		}
		monthlyCalAdapter.notifyDataSetChanged();
		severalCalAdapter.notifyDataSetChanged();
		dailyCalEventListAdapter.notifyDataSetChanged();
		
	};

	private void loadDailyEvent() {
		// display event list
		dailyCalEventListAdapter.clear();

		ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(selectedYear, selectedMonth, selectedDay);
		if (list.size() == 0) {
//			Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_SHORT).show();
		} else {
			for (int i = 0; i < list.size(); i++) {
				// System.out.println(list.get(i).GetStartHour() + ":" +
				// list.get(i).GetStartMin());
				// Toast.makeText(MainActivity.this, list.get(i).GetStartHour()
				// + ":" + list.get(i).GetStartMin(),
				// Toast.LENGTH_SHORT).show();
				dailyCalEventListAdapter.addItem(list.get(i));
			}
			
			dailyCalEventList.setAdapter(dailyCalEventListAdapter);
			dailyCalEventList.setOnItemClickListener(onEventListItemClickListener);
		}
//		dailyCalEventList.setAdapter(dailyCalEventListAdapter);
	}

	private void loadSeveralEvent() {
		// display event list
		severalCalEventListAdapter.clear();

		ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(selectedYear, selectedMonth, selectedDay);
		if (list.size() == 0) {
//			Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_SHORT).show();
		} else {
			for (int i = 0; i < list.size(); i++) {
				// System.out.println(list.get(i).GetStartHour() + ":" +
				// list.get(i).GetStartMin());
				// Toast.makeText(MainActivity.this, list.get(i).GetStartHour()
				// + ":" + list.get(i).GetStartMin(),
				// Toast.LENGTH_SHORT).show();
				severalCalEventListAdapter.addItem(list.get(i));
			}
			
			severalCalEventList.setAdapter(severalCalEventListAdapter);
			severalCalEventList.setOnItemClickListener(onEventListItemClickListener);
		}
//		severalCalEventList.setAdapter(severalCalEventListAdapter);
	}
	
	private void loadEventRange() {
		flag = PRINT_EVENT_RANGE;
		
		eventListAdapter.clear();
		
		ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(eventCalendar.startYear, eventCalendar.startMonth, eventCalendar.startDay, 
				eventCalendar.endYear, eventCalendar.endMonth, eventCalendar.endDay);
		if (list.size() == 0) {
			eventCalEventList.setAdapter(eventListAdapter);
//			Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_SHORT).show();
		} else {
			for (int i = 0; i < list.size(); i++) {
				eventListAdapter.addItem(list.get(i));
			}

			eventCalEventList.setAdapter(eventListAdapter);
			eventCalEventList.setOnItemClickListener(onEventListItemClickListener);
		}
	}
	
	private void loadEventNumber() {
		flag = PRINT_EVENT_NUMBER;
		
		String eventNumberStr = (eventNumber.getText().toString().equals(""))? null : eventNumber.getText().toString();
		
		if (eventNumberStr != null)
			eventCalendar.eventNumber = Integer
					.parseInt(eventNumberStr);

		eventListAdapter.clear();

		int day = fortoday.get(fortoday.DATE);
		int month = fortoday.get(fortoday.MONTH) + 1;
		int year = fortoday.get(fortoday.YEAR);
		
		ArrayList<SingleEvent> list = PractiCalEventList.practiCalEventList.Search(year, month, day, eventCalendar.eventNumber);
		if (list.size() == 0) {
			eventCalEventList.setAdapter(eventListAdapter);
//			Toast.makeText(MainActivity.this, "No events", Toast.LENGTH_SHORT).show();
		} else {
			for (int i = 0; i < list.size(); i++) {
				eventListAdapter.addItem(list.get(i));
			}

			eventCalEventList.setAdapter(eventListAdapter);
			eventCalEventList.setOnItemClickListener(onEventListItemClickListener);
		}
	}

	private DatePickerDialog.OnDateSetListener mMonthlyCalDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			fortoday.set(year, monthOfYear, dayOfMonth);
			selectedDay = dayOfMonth;
			selectedMonth = monthOfYear + 1;
			selectedYear = year;
			selectedDayOfWeek = convertDaytoString(fortoday
					.get(fortoday.DAY_OF_WEEK) - 1);

			monthlyCalTop.setText(selectedYear + " - " + selectedMonth + " - "
					+ selectedDay + ", " + selectedDayOfWeek);
			monthlyCalAdapter.setSpecificMonth(year, monthOfYear, dayOfMonth);
			monthlyCalAdapter.notifyDataSetChanged();
		}
	};

	private String convertDaytoString(int num) {

		String temp = new String();
		switch (num) {
		case 0:
			temp = "SUN";
			break;
		case 1:
			temp = "MON";
			break;
		case 2:
			temp = "TUE";
			break;
		case 3:
			temp = "WED";
			break;
		case 4:
			temp = "TUR";
			break;
		case 5:
			temp = "FRI";
			break;
		case 6:
			temp = "SAT";
			break;
		}
		;
		return temp;
	}
}
