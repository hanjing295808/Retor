package com.tedu.word.adapter;

import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tedu.word.R;
import com.tedu.word.entity.Word;

public class WordAdapter extends BaseAdapter<Word> {

	public WordAdapter(Context context, List<Word> data) {
		super(context, data);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Word word = getData().get(position);
		
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.word_item, null);
			holder.tvEn = (TextView) convertView.findViewById(R.id.tv_word_item_en);
			holder.tvZh = (TextView) convertView.findViewById(R.id.tv_word_item_zh);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tvEn.setText(word.getEn());
		holder.tvZh.setText(word.getZh());
		
		return convertView;
	}
	
	class ViewHolder {
		TextView tvEn;
		TextView tvZh;
	}

}
