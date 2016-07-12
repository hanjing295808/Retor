package com.tedu.android_day0712_progressbar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SecondActivity extends Activity {
	private ProgressBar pb;
	MyAsync async;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		async = new MyAsync();
		async.execute();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (async != null && async.getStatus() == AsyncTask.Status.RUNNING) {
			// cancel方法只是将对应的AsyncTask标记为cancel状态。
			async.cancel(true);
		}
	}

	private class MyAsync extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			// 模拟进度更新
			for (int i = 0; i < 100; i++) {
				if (isCancelled()) {
					break;
				}
				publishProgress(i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			if(isCancelled()){
				return;
			}
			pb.setProgress(values[0]);
		}
	}
}
