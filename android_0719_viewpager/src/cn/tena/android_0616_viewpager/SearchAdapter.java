package cn.tena.android_0616_viewpager;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter{
	private Context context;
	private List<SearchChengYu> searchs;
	private LayoutInflater inflater;
	public SearchAdapter(Context context,List<SearchChengYu> searchs){
		this.context=context;
		this.searchs=searchs;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return searchs.size();
	}

	@Override
	public SearchChengYu getItem(int position) {
		// TODO Auto-generated method stub
		return searchs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.item_search, null);
			holder=new ViewHolder();
			//获取控件
			holder.tvId=(TextView) convertView.findViewById(R.id.tv_id);
			holder.tvName=(TextView) convertView.findViewById(R.id.tv_name);
			//设置tag
			convertView.setTag(holder);
			
		}else{
			//获取tag
			holder=(ViewHolder) convertView.getTag();
		}
		SearchChengYu chengyu=getItem(position);
		holder.tvId.setText(chengyu.getId());
		holder.tvName.setText(chengyu.getName());
		return convertView;
	}
	
	class ViewHolder{
		TextView tvId;
		TextView tvName;
	}
}
