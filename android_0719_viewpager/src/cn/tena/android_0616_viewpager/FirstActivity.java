package cn.tena.android_0616_viewpager;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity implements OnClickListener{
	private Button btn;
	private long lastBackTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		Animation animation1=AnimationUtils.loadAnimation(this, R.anim.rotate);
		btn=(Button) findViewById(R.id.button1);
		btn.startAnimation(animation1);
		btn.setOnClickListener(this);
		//判断是否联网
		haveNet(getBaseContext());
		Handler handler=new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent=new Intent(FirstActivity.this,MainActivity.class);
				startActivity(intent);
			}
		}, 5000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button1:
			Intent intent=new Intent(this,MainActivity.class);
			startActivity(intent);
			break;
			
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - lastBackTime < 2000) {
				finish();
			}
			lastBackTime = System.currentTimeMillis();
			Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
		}
		return true;
	}
	
	//判断网络连接情况	
    public boolean haveNet(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) {
        	Toast.makeText(this, "请检查网络是否连接正常？", Toast.LENGTH_SHORT).show();
            return false;
        }

        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null || !ni.isAvailable()) {
        	Toast.makeText(this, "请检查网络是否连接正常？", Toast.LENGTH_SHORT).show();
        	return false;
        }
        Toast.makeText(this,"网络连接正常，可以上网", Toast.LENGTH_SHORT).show();
        return true;
    }

}
