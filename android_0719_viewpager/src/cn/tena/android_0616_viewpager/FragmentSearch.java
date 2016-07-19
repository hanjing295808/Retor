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

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentSearch extends Fragment implements TextWatcher {
	private EditText etSearch;
	private Button btnSearch;
	private Button btnDelete;
	private ListView lv;
	private SearchAdapter adapter;
	private List<SearchChengYu> chengyus;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SEND_MESSAGE_SUCCESS:
				setAdapter();
				break;
			case SEND_MESSAGE_FAIL:
				Toast.makeText(getActivity(),
						"Json解析失败,错误码：" + msg.arg1 + ",reason:" + msg.obj,
						Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};
	private static final int SEND_MESSAGE_SUCCESS = 101;
	private static final int SEND_MESSAGE_FAIL = 102;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_fragment_search, null);
		setViews(view);
		setListener();
		etSearch.addTextChangedListener(this);
		return view;
	}

	protected void setAdapter() {
		adapter = new SearchAdapter(getActivity(), chengyus);
		lv.setAdapter(adapter);
	}

	private void setListener() {
		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread() {
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

		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				etSearch.setText(null);
				btnDelete.setVisibility(View.GONE);
			}
		});
	}

	protected void loadData() throws IOException, JSONException {
		// 将输入框中的内容转为utf-8格式
		String str = Uri.encode(etSearch.getText().toString(), "utf-8");
		// URL
		URL url = new URL(
				"http://api.avatardata.cn/ChengYu/Search?key=fcfb2e694f75488f964c6bc92b576b55&keyWord="
						+ str);
		// httpURLConnection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// GET
		conn.setRequestMethod("GET");
		// inputStream
		InputStream is = conn.getInputStream();
		// 解析
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String jsonText = sb.toString();
		Log.i("info", "jsonText --> " + jsonText);
		// json解析
		JSONObject obj = new JSONObject(jsonText);
		chengyus = new ArrayList<SearchChengYu>();
		String reason = obj.getString("reason");
		if ("Succes".equals(reason)) {
			// 创建实体类对象
			JSONObject obj2;
			JSONArray ary = obj.getJSONArray("result");
			for (int i = 0; i < ary.length(); i++) {
				obj2 = ary.getJSONObject(i);
				SearchChengYu cheng = new SearchChengYu();
				cheng.setId(obj2.getString("id"));
				cheng.setName(obj2.getString("name"));
				chengyus.add(cheng);
			}
			Log.i("info", "chengyus --> " + chengyus);
			handler.sendEmptyMessage(SEND_MESSAGE_SUCCESS);
		} else {
			Message msg = new Message();
			msg.arg1 = (Integer) obj.get("error_code");
			msg.obj = obj.getString("reason");
			msg.what = SEND_MESSAGE_FAIL;
			handler.sendMessage(msg);
		}
	}

	private void setViews(View view) {
		etSearch = (EditText) view.findViewById(R.id.editText1);
		btnSearch = (Button) view.findViewById(R.id.button1);
		lv = (ListView) view.findViewById(R.id.listView1);
		btnDelete = (Button) view.findViewById(R.id.btn_delete);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// 当edittext里有内容时
		if (s.length() > 0) {
			btnDelete.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

	}

}
