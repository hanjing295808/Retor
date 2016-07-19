package cn.tena.android_0616_viewpager;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChengyuAdapter extends BaseAdapter{
	private List<Chengyu> chengyus;
	private Context context;
	private LayoutInflater inflater;
	public ChengyuAdapter(Context context,List<Chengyu> chengyus){
		this.context=context;
		this.chengyus=chengyus;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return chengyus.size();
	}

	@Override
	public Chengyu getItem(int position) {
		// TODO Auto-generated method stub
		return chengyus.get(position);
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
			convertView=inflater.inflate(R.layout.item_chengyu,null);
			holder=new ViewHolder();
			holder.tvName=(TextView) convertView.findViewById(R.id.tv_name);
			holder.tvSpell=(TextView) convertView.findViewById(R.id.tv_spell);
			holder.tvContent=(TextView) convertView.findViewById(R.id.tv_content);
			holder.tvDeriVation=(TextView) convertView.findViewById(R.id.tv_derivation);
			holder.tvSample=(TextView) convertView.findViewById(R.id.tv_sample);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		Chengyu chengyu=getItem(position);
		holder.tvName.setText(chengyu.getName());
		holder.tvSpell.setText(chengyu.getSpell());
		holder.tvContent.setText(chengyu.getContent());
		holder.tvDeriVation.setText(chengyu.getDerivation());
		holder.tvSample.setText(chengyu.getSamples());

		return convertView;
	}
	class ViewHolder{
		TextView tvName;
		TextView tvSpell;
		TextView tvContent;
		TextView tvDeriVation;
		TextView tvSample;
	}
}










