package cn.tena.android_0616_viewpager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private List<Fragment> fragments;
	private ViewPager vp;
	private MyPagerAdapter adapter;
	private RadioGroup rg;
	private RadioButton rb0;
	private RadioButton rb1;
	private RadioButton rb2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setView();
		setAdapter();
		setListener();
	}

	private void setListener() {
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				switch(arg0){
				case 0:
					rb0.setChecked(true);
					break;
				case 1:
					rb1.setChecked(true);
					break;
				case 2:
					rb2.setChecked(true);
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.radio0:
					vp.setCurrentItem(0);
					break;
				case R.id.radio1:
					vp.setCurrentItem(1);
					break;
				case R.id.radio2:
					vp.setCurrentItem(2);
					break;
				}
			}
		});
	}

	private void setAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new FragmentMingYan());
		fragments.add(new FragmentChengYu());
		fragments.add(new FragmentSearch());
		adapter = new MyPagerAdapter(getSupportFragmentManager());
		//…Ë÷√ViewPager∂Øª≠
		//vp.setPageTransformer(true, new ZoomOutPageTransformer());
		vp.setPageTransformer(true, new DepthPageTransformer());
		vp.setAdapter(adapter);
	}

	private void setView() {
		vp = (ViewPager) findViewById(R.id.vp);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		rb0 = (RadioButton) findViewById(R.id.radio0);
		rb1 = (RadioButton) findViewById(R.id.radio1);
		rb2=(RadioButton) findViewById(R.id.radio2);
	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragments.size();
		}

	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
