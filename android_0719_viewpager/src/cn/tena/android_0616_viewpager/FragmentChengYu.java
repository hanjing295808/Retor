package cn.tena.android_0616_viewpager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class FragmentChengYu extends Fragment {
	private ListView lv;
	private List<Chengyu> chengyus;
	private ChengyuAdapter adapter;
	private Button btnRandom;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case HANDLER_SEND_CHENGYU_SUCCESS:
				setAdapter();
				break;
			}
		};
	};
	public static final int HANDLER_SEND_CHENGYU_SUCCESS=1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.activity_fragment_cheng_yu,null);
		setView(view);
		setListener();
		
		return view;
	}
	//按钮的监听事件
	private void setListener() {
		btnRandom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ChengyuList();
			}
		});
		
	}
	protected void setAdapter() {
		adapter=new ChengyuAdapter(getActivity(),chengyus);
		lv.setAdapter(adapter);
	}

	
	
	private void ChengyuList() {
		new Thread(){
			public void run() {
				try {
					query();
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

	protected void query() throws IOException, JSONException {
		//get请求  URL
//		URL url=new URL("http://apis.baidu.com/avatardata/chengyu/lookup?" +
//				"dtype=JOSN&id=d420b457-4b86-4ab1-b824-cb84440131fc");
		URL url=new URL(" http://api.avatardata.cn/ChengYu/Random?key=fcfb2e694f75488f964c6bc92b576b55");
		//HttpURLConnection
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		//GET
		conn.setRequestMethod("GET");
		// 填入apikey到HTTP header
		//conn.setRequestProperty("apikey",  "cb56752248ae0d897acad1d31d49a53c");
		//conn.setRequestProperty("apikey","fcfb2e694f75488f964c6bc92b576b55");
		conn.connect();
		//inputstream
		InputStream is=conn.getInputStream();

		//解析
		BufferedReader reader=new BufferedReader(new InputStreamReader(is));
		StringBuffer sb=new StringBuffer();
		String line=null;
		while((line=reader.readLine())!=null){
			sb.append(line);
		}
		String jsonText=sb.toString();
		Log.i("info", "jsonText ---> "+jsonText);
		//json解析
		JSONObject obj=new JSONObject(jsonText);
		chengyus=new ArrayList<Chengyu>();
		JSONObject obj1=obj.getJSONObject("result");
		//需要有一个实体类 封装json数据
		Chengyu chengyu=new Chengyu();
		chengyu.setId(obj1.getString("id"));
		chengyu.setName(obj1.getString("name"));
		chengyu.setSpell(obj1.getString("spell"));
		chengyu.setContent(obj1.getString("content"));
		chengyu.setDerivation(obj1.getString("derivation"));
		chengyu.setSamples(obj1.getString("samples"));


		chengyus.add(chengyu);



		handler.sendEmptyMessage(HANDLER_SEND_CHENGYU_SUCCESS);
	}

	private void setView(View view) {
		lv=(ListView) view.findViewById(R.id.listView1);
		btnRandom=(Button) view.findViewById(R.id.btn_random);
	}
}
