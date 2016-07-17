package com.tarena.android_day0717_recyclerview;

import java.util.ArrayList;
import java.util.List;




import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder>{
	private Context context;
	private List<String> mDatas;
	private LayoutInflater inflater;
	private List<Integer> height;
	public StaggeredAdapter(Context context,List<String> mDatas) {
		this.context=context;
		this.mDatas=mDatas;
		inflater=LayoutInflater.from(context);
		height=new ArrayList<Integer>();
		for(int i=0;i<mDatas.size();i++){
			height.add((int)(100+Math.random()*300));
		}
	}
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public void onBindViewHolder(StaggeredAdapter.MyViewHolder holder, int position) {		
		LayoutParams lp=holder.itemView.getLayoutParams();
		lp.height=height.get(position);
		holder.itemView.setLayoutParams(lp);
		holder.tv.setText(mDatas.get(position));
		
	}

	@Override
	public StaggeredAdapter.MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view=inflater.inflate(R.layout.item_single_textview, arg0,false);
		MyViewHolder holder=new MyViewHolder(view);
		return holder;
	}
	class MyViewHolder extends ViewHolder{
		TextView tv;
		public MyViewHolder(View view) {
			super(view);
			tv=(TextView) view.findViewById(R.id.tv);
		}
		
	}
	
}

