package cn.tena.android_day0713_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	public static final int SEND_DATA = 0;
	public static final int SEND_DATA_COMPLETED = 1;
	private Button btnProgress;
	private ProgressBar pb;
	private Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what){
			case SEND_DATA:
				int progress=(Integer) msg.obj;
				pb.setProgress(progress);
				btnProgress.setEnabled(false);
				break;
			case SEND_DATA_COMPLETED:
				btnProgress.setEnabled(true);
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnProgress=(Button) findViewById(R.id.btn_progress);
		pb=(ProgressBar) findViewById(R.id.progressBar1);
	}
	
	public void doClick(View v){
		InnerThread th=new InnerThread();
		th.start();
	}
	private class InnerThread extends Thread{
		Message msg;
		@Override
		public void run() {
			for(int i=0;i<100;i++){
				Message msg=Message.obtain();
				msg.obj=i;
				msg.what=SEND_DATA;
				handler.sendMessage(msg);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Message msg=Message.obtain();
			msg.what=SEND_DATA_COMPLETED;
			handler.sendMessage(msg);
		}
	}

}
