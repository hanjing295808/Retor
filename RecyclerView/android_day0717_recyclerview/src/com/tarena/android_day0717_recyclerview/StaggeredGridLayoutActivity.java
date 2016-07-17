package com.tarena.android_day0717_recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;



public class StaggeredGridLayoutActivity extends Activity {
	private RecyclerView recyclerView;
	private StaggeredAdapter adapter;
	private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        	initViews();
        	intiData();
        	adapter=new StaggeredAdapter(this,mDatas); 
        	recyclerView.setAdapter(adapter);
        	//设置RecyclerView的布局管理器
        	recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
        			StaggeredGridLayoutManager.VERTICAL));
        	//recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
		}
	private void initViews() {
		recyclerView=(RecyclerView) findViewById(R.id.id_recyclerView);
	}
	private void intiData() {
		mDatas=new ArrayList<String>();
		for(int i='A';i<='Z';i++){
			mDatas.add(""+(char)i);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		int id=item.getItemId();
		switch(id){
		}
		return false;
	}
    
}








































