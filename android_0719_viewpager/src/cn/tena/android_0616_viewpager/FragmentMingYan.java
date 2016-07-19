package cn.tena.android_0616_viewpager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;






import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentMingYan extends Fragment {
	private ListView lv;
	private List<MingYan> mingYans;
	private MingYanAdapter adapter;
	private Button btnSearch;
	private TextView tvContent;
	private TextView tvContentTitle;
	private TextView tvName;
	private TextView tvNameTitle;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case REQUEST_MINGYAN_SUCCESS:
				setAdapter();
				break;
			case REQUEST_MINGYAN_FAIL:
				Toast.makeText(getActivity(),"请求失败,返回码："+msg.obj,Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};
	private static final int REQUEST_MINGYAN_SUCCESS = 101;
	private static final int REQUEST_MINGYAN_FAIL=102;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_fragment_ming_yan,null);
		setView(view);
		setListener();
		return view;
	}
	private void setListener() {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			tvContentTitle=(TextView) lv.getChildAt(0).findViewById(R.id.tv_content_title);
			tvContent=(TextView) lv.getChildAt(0).findViewById(R.id.tv_content);
			tvNameTitle=(TextView) lv.getChildAt(0).findViewById(R.id.tv_name_title);
			tvName=(TextView) lv.getChildAt(0).findViewById(R.id.tv_name);
			String strContentTitle=tvContentTitle.getText().toString();
			String strContent=(String) tvContent.getText().toString();
			String strNameTitle=tvNameTitle.getText().toString();
			String strName=tvName.getText().toString();
			
			shareText(strContentTitle+strContent+"\n"+strNameTitle+strName);
			}
			
		});
		btnSearch.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new Thread(){
				public void run() {
					try {
						loadData();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
		}
	});
	}
	private void shareText(String string){
		Intent intent=new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, string);
		startActivity(Intent.createChooser(intent,"分享到..."));
	}
	private void setView(View view) {
		btnSearch=(Button) view.findViewById(R.id.btnSearch);
		lv=(ListView) view.findViewById(R.id.listView1);
//		tvContent=(TextView) view.findViewById(R.id.tv_content);
//		tvName=(TextView) view.findViewById(R.id.tv_name);
	}
	
	protected void setAdapter() {
		adapter=new MingYanAdapter(getActivity(), mingYans);
		lv.setAdapter(adapter);		
	}
	
	protected void loadData() throws IOException, JSONException {
		String httpUrl = "http://apis.baidu.com/txapi/dictum/dictum";
		String httpArg = "";
		httpUrl = httpUrl + "?" + httpArg;
		//URL
		URL url=new URL(httpUrl);
		//HttpURLConnection
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		//GET
		conn.setRequestMethod("GET");		
		 // 填入apikey到HTTP header
        conn.setRequestProperty("apikey",  "bd538f706a66feb1bb643d892dcd696f");
        //获取服务端返回的InputStream
        InputStream is=conn.getInputStream();
        //将输入流转成字符串
        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String line="";
        while((line=reader.readLine())!=null){
        	sb.append(line);
        }
		String jsonText=sb.toString();
		Log.i("info","jsonText --> "+jsonText);
		//解析
		JSONObject obj=new JSONObject(jsonText);
		mingYans=new ArrayList<MingYan>();
		String str=obj.getString("msg");
		if("success".equals(str)){
			JSONArray ary=obj.getJSONArray("newslist");
			for(int i=0;i<ary.length();i++){
				JSONObject obj2=ary.getJSONObject(i);
				MingYan ming=new MingYan();
				ming.setId(obj2.getString("id"));
				ming.setContent(obj2.getString("content"));
				ming.setName(obj2.getString("mrname"));
				mingYans.add(ming);
			}
			handler.sendEmptyMessage(REQUEST_MINGYAN_SUCCESS);
			Log.i("info","mingyan --->"+mingYans);
		}else{
			//请求失败  
			Message msg = new Message();
			msg.what = REQUEST_MINGYAN_FAIL;
			msg.obj = obj.getString("code");
			handler.sendMessage(msg );
		}
	}
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onActivityCreated(savedInstanceState);
//		btnSearch=(Button) getActivity().findViewById(R.id.btnSearch);
//		lv=(ListView) getActivity().findViewById(R.id.listView1);
//		btnSearch.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				new Thread(){
//					public void run() {
//						try {
//							loadData();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (JSONException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					};
//				}.start();
//			}
//		});
//	}
}
