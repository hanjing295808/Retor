package com.tedu.android_day0713_startactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RequestActivity extends Activity{
	private Button btn;
	private EditText et1;
	private EditText et2;
	public static final String KEY_USER_ID="KEY_USER_ID";
	public static final String KEY_USER_PASSWORD="KEY_USER_PASSWORD";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request_activity);
		btn=(Button) findViewById(R.id.button1);
		et1=(EditText) findViewById(R.id.editText1);
		et2=(EditText) findViewById(R.id.editText2);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.putExtra(KEY_USER_ID, et1.getText().toString());
				intent.putExtra(KEY_USER_PASSWORD, et2.getText().toString());
				Log.i("info","et1 --> "+et1.getText().toString()+" et2 -->"+et2.getText().toString());
				setResult(RESULT_OK,intent);
				finish();
			}
		});
	}
}
