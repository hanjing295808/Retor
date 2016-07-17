package com.tarena.android_day0717_recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends Activity {
	private RecyclerView recyclerView;
	private SimpleAdapter adapter;
	private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        	initViews();
        	intiData();
        	adapter=new SimpleAdapter(this,mDatas); 
        	recyclerView.setAdapter(adapter);
        	//
        	LinearLayoutManager manager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        	recyclerView.setLayoutManager(manager);
        	recyclerView.setItemAnimator(new DefaultItemAnimator());
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
		case R.id.action_add:
			adapter.addData(1);
			break;
		case R.id.action_delete:
			adapter.deleteData(1);
			break;
		case R.id.action_gridview:
			recyclerView.setLayoutManager(new GridLayoutManager(this,3));
			break;
		case R.id.action_listview:
			recyclerView.setLayoutManager(new LinearLayoutManager(this));
			break;
		case R.id.action_staggered:
			Intent intent=new Intent(this,StaggeredGridLayoutActivity.class);
			startActivity(intent);
			break;
		case R.id.action_hor_gridview:
			recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
			break;
		default:
			break;
		}
		return false;
	}
    
}








































