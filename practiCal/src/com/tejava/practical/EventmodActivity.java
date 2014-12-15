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
import android.widget.Toast;

public class EventmodActivity extends Activity {

	/* variable declaration */

	// common value
	int operation_mode; // 0: modify, 1: new
	
	int event_id;
	int event_year;
	int event_month;
	int event_day;
	int event_startHour;
	int event_startMin;
	int event_endHour;
	int event_endMin;
	String event_name;
	String event_description;
	String event_location;
	int event_important;
	int event_group;
	int event_color;
	
	GregorianCalendar today_info;

	// EditTexts
	EditText ET_event_name;
	EditText ET_event_description;
	EditText ET_event_location;
	EditText ET_event_important;
	EditText ET_event_group_number;
	EditText ET_event_color;

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
	
	static final int SEVERAL_CAL_EVENT_LIST = 30;
	static final int DAILY_CAL_EVENT_LIST = 31;
	static final int EVENT_CAL_EVENT_LIST = 32;
	
	static final int MODIFY = 99;

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
		start_month = today_info.get(today_info.MONTH) + 1;
		start_day = today_info.get(today_info.DAY_OF_MONTH);
		start_hour = today_info.get(today_info.HOUR_OF_DAY);
		start_min = today_info.get(today_info.MINUTE);

		end_year = today_info.get(today_info.YEAR);
		end_month = today_info.get(today_info.MONTH) + 1;
		end_day = today_info.get(today_info.DAY_OF_MONTH);
		end_hour = today_info.get(today_info.HOUR_OF_DAY);
		end_min = today_info.get(today_info.MINUTE);

		ET_event_name = (EditText) findViewById(R.id.eventmod_event_name);
		ET_event_description = (EditText) findViewById(R.id.eventmod_description);
		ET_event_location = (EditText) findViewById(R.id.eventmod_loc);
		ET_event_important = (EditText) findViewById(R.id.eventmod_isIPT);
		ET_event_group_number = (EditText) findViewById(R.id.eventmod_eGnum);
		ET_event_color = (EditText) findViewById(R.id.eventmod_color);

		btn_start_day = (Button) findViewById(R.id.btn_eventmod_start_day);
		btn_start_time = (Button) findViewById(R.id.btn_eventmod_start_time);
		btn_end_day = (Button) findViewById(R.id.btn_eventmod_end_day);
		btn_end_time = (Button) findViewById(R.id.btn_eventmod_end_time);
		btn_ok = (Button) findViewById(R.id.btn_eventmod_ok);
		btn_delete = (Button) findViewById(R.id.btn_eventmod_del);

		
		Intent intent = getIntent();
		operation_mode = intent.getExtras().getInt("mode_setting", 1);
		Bundle event_bundle = intent.getExtras();
		
		if (operation_mode == 1) { // new
			btn_start_day.setText(start_year+"/"+start_month+"/"+start_day);
			btn_start_time.setText(start_hour+":"+start_min);
			btn_end_day.setText(end_year+"/"+end_month+"/"+end_day);
			btn_end_time.setText(end_hour+":"+end_min);
			
		} else // modify
		{
			SingleEvent event_object = (SingleEvent) event_bundle.getSerializable("event_obj");
			
			event_id = event_object.GetId();
			event_year = event_object.GetYear();
			event_month = event_object.GetMonth();
			event_day = event_object.GetDay();
			event_startHour = event_object.GetStartHour();
			event_startMin = event_object.GetStartMin();
			event_endHour = event_object.GetEndHour();
			event_endMin = event_object.GetEndMin();
			event_name = event_object.GetName();
			event_description = event_object.GetDescription();
			// TODO: modify
			event_location = "locloclocloc";
			event_important = 4444;
			event_group = 666;
			event_color = 6666;

			// why buffer? ... sometimes later expand end date...
			start_year = event_year;
			start_month = event_month;
			start_day = event_day;
			start_hour = event_startHour;
			start_min = event_startMin;

			end_year = event_year;
			end_month = event_month;
			end_day = event_day;
			end_hour = event_endHour;
			end_min = event_endMin;
			
			btn_start_day.setText(start_year+"/"+start_month+"/"+start_day);
			btn_start_time.setText(start_hour+":"+start_min);
			btn_end_day.setText(end_year+"/"+end_month+"/"+end_day);
			btn_end_time.setText(end_hour+":"+end_min);
			
			ET_event_name.setText(event_name);
			ET_event_description.setText(event_description);
			ET_event_location.setText(event_location);
			ET_event_important.setText(Integer.toString(event_important));
			ET_event_group_number.setText(Integer.toString(event_group));
			ET_event_color.setText(Integer.toString(event_color));
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
				
				if (operation_mode == 1) {	// new
					try {
						PractiCalEventList.practiCalEventList.Insert(start_year, start_month, start_day, 
								start_hour, end_hour, start_min, end_min, 
								ET_event_name.getText().toString(), ET_event_description.getText().toString(),
								666, 6666, 4444, "locloclocloc");
						PractiCalEventList.practiCalEventList.Save("save.txt");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Intent intent = getIntent();
					int addFrom = intent.getExtras().getInt("add_from");

					setResult(addFrom);
				}
				else {	// modify
					try {
						SingleEvent event = new SingleEvent(event_id, start_year, start_month, start_day, 
								start_hour, end_hour, start_min, end_min, 
								ET_event_name.getText().toString(), ET_event_description.getText().toString(),
								666, 6666, 4444, "locloclocloc");
						
						PractiCalEventList.practiCalEventList.Edit(event);
						PractiCalEventList.practiCalEventList.Save("save.txt");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					setResult(MODIFY);
				}
				
				finish();
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
					try {
						PractiCalEventList.practiCalEventList.Delete(event_id);
						PractiCalEventList.practiCalEventList.Save("save.txt");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					setResult(MODIFY);
				}
				
				finish();
			 }
		});
		
	}

	private DatePickerDialog.OnDateSetListener mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			start_year = year;
			start_month = monthOfYear+1;
			start_day = dayOfMonth;
			
			btn_start_day.setText(start_year+"/"+start_month+"/"+start_day);
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
			end_month = monthOfYear+1;
			end_day = dayOfMonth;
			
			btn_end_day.setText(end_year+"/"+end_month+"/"+end_day);
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
