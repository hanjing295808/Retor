package cn.tena.android_day0711_textview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvNumber=(TextView) findViewById(R.id.tv_number);
		TimerThread tt=new TimerThread();
		tt.start();
	}
	
	private class TimerThread extends Thread{
		int i;
		@Override
		public void run() {			
				for(i=10;i>=1;i--){
					runOnUiThread(new Runnable() {
						public void run() {
							tvNumber.setText(" "+i);
						}
					});
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(i==1){
						Intent intent=new Intent(MainActivity.this,SecondActivity.class);
						startActivity(intent);
					}
				}

		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
