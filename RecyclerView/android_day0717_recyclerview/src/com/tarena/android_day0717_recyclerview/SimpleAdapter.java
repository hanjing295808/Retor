package com.tarena.android_day0717_recyclerview;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder>{
	private Context context;
	private List<String> mDatas;
	private LayoutInflater inflater;
	public SimpleAdapter(Context context,List<String> mDatas) {
		this.context=context;
		this.mDatas=mDatas;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}
	public void addData(int pos){
		mDatas.add(pos,"Insert one");
		notifyItemInserted(pos);
	}
	public void deleteData(int pos){
		mDatas.remove(pos);
		notifyItemRemoved(pos);
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		
		holder.tv.setText(mDatas.get(position));
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view=inflater.inflate(R.layout.item_single_textview, arg0,false);
		MyViewHolder holder=new MyViewHolder(view);
		return holder;
	}
	
}
class MyViewHolder extends ViewHolder{
	TextView tv;
	public MyViewHolder(View view) {
		super(view);
		tv=(TextView) view.findViewById(R.id.tv);
	}
	
}
