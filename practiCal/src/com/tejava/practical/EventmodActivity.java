package com.tejava.practical;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EventmodActivity extends Activity {

	/* variable declaration */

	int operation_mode; // 0: modify, 1: new
	String db_access_info;

	EditText ET_event_name;
	EditText ET_event_description;
	EditText ET_event_additional_memo_1;
	EditText ET_event_additional_memo_2;
	EditText ET_event_additional_memo_3;
	EditText ET_event_additional_memo_4;

	Button btn_ok;

	// Button btn_cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventmod_activity);

		variableInitialize();

		listenerInitialize();
	}

	private void variableInitialize() {

		ET_event_name = (EditText) findViewById(R.id.eventmod_event_name);
		ET_event_description = (EditText) findViewById(R.id.eventmod_description);
		ET_event_additional_memo_1 = (EditText) findViewById(R.id.eventmod_adm_1);
		ET_event_additional_memo_2 = (EditText) findViewById(R.id.eventmod_adm_2);
		ET_event_additional_memo_3 = (EditText) findViewById(R.id.eventmod_adm_3);
		ET_event_additional_memo_4 = (EditText) findViewById(R.id.eventmod_adm_4);

		btn_ok = (Button) findViewById(R.id.btn_eventmod_ok);
		// btn_cancel = (Button) findViewById(R.id.btn_eventmod_cancel);

		Intent intent = getIntent();

		operation_mode = intent.getExtras().getInt("mode_setting", 1);

		if (operation_mode == 1) { // new
			// need initializing?
		} else // modify
		{
			db_access_info = intent.getExtras().getString("db_access_info");

			ET_event_name.setText("db_access_info.event_name");
			ET_event_description.setText(db_access_info);
			ET_event_additional_memo_1.setText("db_access_info.event_additional_memo_1");
			ET_event_additional_memo_2.setText("db_access_info.event_additional_memo_2");
			ET_event_additional_memo_3.setText("db_access_info.event_additional_memo_3");
			ET_event_additional_memo_4.setText("db_access_info.event_additional_memo_4");
		}

	}

	private void listenerInitialize() {
		// five function button's OnClickListener

		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// save modified data
			}
		});

		/*
		 * btn_cancel.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * } });
		 */
	}
}
