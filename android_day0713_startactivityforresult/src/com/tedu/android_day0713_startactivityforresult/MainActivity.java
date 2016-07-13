package com.tedu.android_day0713_startactivityforresult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn;
	private TextView tv;
	private static final int REQUEST=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn=(Button) findViewById(R.id.button1);
		tv=(TextView) findViewById(R.id.textView1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,RequestActivity.class);
				//发送意图标识为REQUES=1
				startActivityForResult(intent, REQUEST);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("info","===========>");
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//requestCode表示请求的标识，resultCode表示有数据
		if(requestCode==MainActivity.REQUEST&&resultCode==RESULT_OK){
			String str="账号："+data.getStringExtra(RequestActivity.KEY_USER_ID)+"\n"+
		"密码"+data.getStringExtra(RequestActivity.KEY_USER_PASSWORD);
			Log.i("info", "str --> "+str);
			tv.setText(str);
		}
		Toast.makeText(this,"requestCode="+requestCode+":"+"resultCode="+resultCode, 
				Toast.LENGTH_LONG).show();
		Log.i("info", "==========================>");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}






























