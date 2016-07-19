package com.tedu.android_day0717_code;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends Activity {
	private TextView tv;
	private Button btn;
	private Button btnMake;
	private EditText etInput;
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv=(TextView) findViewById(R.id.tv);
		btn=(Button) findViewById(R.id.button1);
		btnMake=(Button) findViewById(R.id.btn_make);
		etInput=(EditText) findViewById(R.id.et_input);
		imageView=(ImageView) findViewById(R.id.imageView1);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(MainActivity.this
						,CaptureActivity.class), 0);
			}
		});
		
		btnMake.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String input=etInput.getText().toString();
				if(input.equals("")){
					Toast.makeText(MainActivity.this, "输入不能为空",Toast.LENGTH_SHORT).show();
					
				}else{
					Bitmap bitmap=EncodingUtils.createQRCode(input,
							500, 500, null);
					imageView.setImageBitmap(bitmap);
				}
				
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			Bundle bundle=data.getExtras();
			String result=bundle.getString("result");
			tv.setText(result);
		}
	}
}
