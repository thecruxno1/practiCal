package com.tejava.practical;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

public class EventmodActivity extends Activity {

	/* variable declaration */

	// common value
	int operation_mode; // 0: modify, 1: new
	String db_access_info;
	GregorianCalendar today_info;

	// EditTexts
	EditText ET_event_name;
	EditText ET_event_description;
	EditText ET_event_additional_memo_1;
	EditText ET_event_additional_memo_2;
	EditText ET_event_additional_memo_3;
	EditText ET_event_additional_memo_4;

	// Buttons
	Button btn_start_day;
	Button btn_start_time;
	Button btn_end_day;
	Button btn_end_time;
	Button btn_ok;
	Button btn_delete;

	// define
	static final int EVENT_MOD_START_DATE_DIALOG_ID = 100;
	static final int EVENT_MOD_START_TIME_DIALOG_ID = 101;
	static final int EVENT_MOD_END_DATE_DIALOG_ID = 110;
	static final int EVENT_MOD_END_TIME_DIALOG_ID = 111;

	// date/time
	int start_year;
	int start_month;
	int start_day;
	int start_hour;
	int start_min;

	int end_year;
	int end_month;
	int end_day;
	int end_hour;
	int end_min;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventmod_activity);

		variableInitialize();

		listenerInitialize();
	}

	private void variableInitialize() {
		today_info = new GregorianCalendar();

		start_year = today_info.get(today_info.YEAR);
		start_month = today_info.get(today_info.MONTH);
		start_day = today_info.get(today_info.DAY_OF_MONTH);
		start_hour = today_info.get(today_info.HOUR_OF_DAY);
		start_min = today_info.get(today_info.MINUTE);

		end_year = today_info.get(today_info.YEAR);
		end_month = today_info.get(today_info.MONTH);
		end_day = today_info.get(today_info.DAY_OF_MONTH);
		end_hour = today_info.get(today_info.HOUR_OF_DAY);
		end_min = today_info.get(today_info.MINUTE);

		ET_event_name = (EditText) findViewById(R.id.eventmod_event_name);
		ET_event_description = (EditText) findViewById(R.id.eventmod_description);
		ET_event_additional_memo_1 = (EditText) findViewById(R.id.eventmod_adm_1);
		ET_event_additional_memo_2 = (EditText) findViewById(R.id.eventmod_adm_2);
		ET_event_additional_memo_3 = (EditText) findViewById(R.id.eventmod_adm_3);
		ET_event_additional_memo_4 = (EditText) findViewById(R.id.eventmod_adm_4);

		btn_start_day = (Button) findViewById(R.id.btn_eventmod_start_day);
		btn_start_time = (Button) findViewById(R.id.btn_eventmod_start_time);
		btn_end_day = (Button) findViewById(R.id.btn_eventmod_end_day);
		btn_end_time = (Button) findViewById(R.id.btn_eventmod_end_time);
		btn_ok = (Button) findViewById(R.id.btn_eventmod_ok);
		btn_delete = (Button) findViewById(R.id.btn_eventmod_del);

		btn_start_day.setText(start_year+"/"+(int)(start_month+1)+"/"+start_day);
		btn_start_time.setText(start_hour+":"+start_min);
		btn_end_day.setText(end_year+"/"+(int)(end_month+1)+"/"+end_day);
		btn_end_time.setText(end_hour+":"+end_min);
		
		Intent intent = getIntent();
		operation_mode = intent.getExtras().getInt("mode_setting", 1);
		if (operation_mode == 1) { // new
			// need initializing?
		} else // modify
		{
			db_access_info = intent.getExtras().getString("db_access_info");

			// must be changed... db_access_info -> date/time
			start_year = 0;
			start_month = 0;
			start_day = 0;
			start_hour = 0;
			start_min = 0;

			end_year = 0;
			end_month = 0;
			end_day = 0;
			end_hour = 0;
			end_min = 0;
			
			btn_start_day.setText(start_year+"/"+(int)(start_month+1)+"/"+start_day);
			btn_start_time.setText(start_hour+":"+start_min);
			btn_end_day.setText(end_year+"/"+(int)(end_month+1)+"/"+end_day);
			btn_end_time.setText(end_hour+":"+end_min);
			
			ET_event_name.setText("db_access_info.event_name");
			ET_event_description.setText(db_access_info);
			ET_event_additional_memo_1
					.setText("db_access_info.event_additional_memo_1");
			ET_event_additional_memo_2
					.setText("db_access_info.event_additional_memo_2");
			ET_event_additional_memo_3
					.setText("db_access_info.event_additional_memo_3");
			ET_event_additional_memo_4
					.setText("db_access_info.event_additional_memo_4");
		}

	}

	private void listenerInitialize() {
		// five function button's OnClickListener

		btn_start_day.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(EVENT_MOD_START_DATE_DIALOG_ID);
			}
		});
		
		btn_start_time.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(EVENT_MOD_START_TIME_DIALOG_ID);
			}
		});
		
		btn_end_day.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(EVENT_MOD_END_DATE_DIALOG_ID);
			}
		});
		
		btn_end_time.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(EVENT_MOD_END_TIME_DIALOG_ID);
			}
		});

		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
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
				
				try {
					PractiCalEventList.practiCalEventList.Insert(start_year, start_month, start_day, 
							start_hour, end_hour, start_min, end_min, ET_event_name.getText().toString(), ET_event_description.getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent intent = new Intent(EventmodActivity.this, MainActivity.class);
				intent.putExtra("mode_setting", 1); // 0: modify, 1: new
//				intent.putExtra("db_access_info", "test_value");
				startActivity(intent);	
			}
		});

		
		 btn_delete.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) {
				if (operation_mode == 1) { // new
					// do nothing
				} else // modify
				{
					// delete event + must be check logic for repeated event
				}
			 }
		});
		
	}

	private DatePickerDialog.OnDateSetListener mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			start_year = year;
			start_month = monthOfYear;
			start_day = dayOfMonth;
			
			btn_start_day.setText(start_year+"/"+(int)(start_month+1)+"/"+start_day);
		}
	};
	private TimePickerDialog.OnTimeSetListener mStartTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			start_hour = hourOfDay;
			start_min = minute;
			
			btn_start_time.setText(start_hour+":"+start_min);
		}
	};
	private DatePickerDialog.OnDateSetListener mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			end_year = year;
			end_month = monthOfYear;
			end_day = dayOfMonth;
			
			btn_end_day.setText(end_year+"/"+(int)(end_month+1)+"/"+end_day);
		}
	};
	private TimePickerDialog.OnTimeSetListener mEndTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			end_hour = hourOfDay;
			end_min = minute;
			
			btn_end_time.setText(end_hour+":"+end_min);
		}
	};
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case EVENT_MOD_START_DATE_DIALOG_ID:
			return new DatePickerDialog(this, mStartDateSetListener, start_year, start_month, start_day);
		case EVENT_MOD_START_TIME_DIALOG_ID:
			return new TimePickerDialog(this, mStartTimeSetListener, start_hour, start_min, true);
		case EVENT_MOD_END_DATE_DIALOG_ID:
			return new DatePickerDialog(this, mEndDateSetListener, end_year, end_month, end_day);
		case EVENT_MOD_END_TIME_DIALOG_ID:
			return new TimePickerDialog(this, mEndTimeSetListener, end_hour, end_min, true);
		}

		return null;
	}

}
