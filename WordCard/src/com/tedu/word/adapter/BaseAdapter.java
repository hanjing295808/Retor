package com.tedu.word.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
	private Context context;
	private List<T> data;
	private LayoutInflater inflater;

	public BaseAdapter(Context context, List<T> data) {
		super();
		setContext(context);
		setData(data);
		setLayoutInflater();
	}

	public final Context getContext() {
		return context;
	}

	private final void setContext(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("参数Context不允许为null！");
		}
		this.context = context;
	}

	public final List<T> getData() {
		return data;
	}

	public final void setData(List<T> data) {
		if (data == null) {
			data = new ArrayList<T>();
		}
		this.data = data;
	}

	public final LayoutInflater getLayoutInflater() {
		return inflater;
	}

	private final void setLayoutInflater() {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
