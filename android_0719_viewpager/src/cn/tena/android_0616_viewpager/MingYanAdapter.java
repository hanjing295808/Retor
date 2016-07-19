package cn.tena.android_0616_viewpager;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MingYanAdapter extends BaseAdapter{
	private List<MingYan> mingYans;
	private Context context;
	private LayoutInflater inflater;
	
	public MingYanAdapter(Context context,List<MingYan> mingYans){
		this.context=context;
		this.mingYans=mingYans;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mingYans.size();
	}

	@Override
	public MingYan getItem(int position) {
		// TODO Auto-generated method stub
		return mingYans.get(position);
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
			convertView=inflater.inflate(R.layout.item_mingyan,null);
			holder=new ViewHolder();
			holder.tvId=(TextView) convertView.findViewById(R.id.tv_id);
			holder.tvContent=(TextView) convertView.findViewById(R.id.tv_content);
			holder.tvName=(TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		MingYan mingyan=getItem(position);
		Log.i("info","mingyan  -->  "+mingyan.getId());
		Log.i("info","holder --> "+holder.hashCode());
		holder.tvId.setText(mingyan.getId());
		holder.tvContent.setText(mingyan.getContent());
		holder.tvName.setText(mingyan.getName());
		return convertView;
	}
	
	class ViewHolder{
		TextView tvId;
		TextView tvContent;
		TextView tvName;
	}
}
