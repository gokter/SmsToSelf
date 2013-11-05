package com.flyingh.smstoself;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.provider.Telephony.Sms;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText addressEditText;
	private EditText bodyEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addressEditText = (EditText) findViewById(R.id.address);
		bodyEditText = (EditText) findViewById(R.id.body);
	}

	@TargetApi(19)
	public void send(View view) {
		String address = addressEditText.getText().toString().trim();
		String body = bodyEditText.getText().toString().trim();
		if (TextUtils.isEmpty(address)) {
			Toast.makeText(this, R.string.address_should_not_be_empty_,
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(body)) {
			Toast.makeText(this, R.string.body_should_not_be_empty_,
					Toast.LENGTH_SHORT).show();
			return;
		}
		ContentValues values = new ContentValues();
		values.put(Sms.ADDRESS, address);
		values.put(Sms.DATE, System.currentTimeMillis());
		values.put(Sms.TYPE, 1);
		values.put(Sms.BODY, body);
		getContentResolver().insert(Sms.CONTENT_URI, values);
		Toast.makeText(this, R.string.send_success_, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
