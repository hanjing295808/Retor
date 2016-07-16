package com.tedu.word.ui;

import java.util.List;

import com.tedu.word.R;
import com.tedu.word.R.layout;
import com.tedu.word.R.menu;
import com.tedu.word.adapter.WordAdapter;
import com.tedu.word.dal.IDao;
import com.tedu.word.dal.WordDao;
import com.tedu.word.entity.Word;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends Activity implements View.OnClickListener,
	DialogInterface.OnClickListener{
	private EditText etEn;
	private EditText etZh;
	private Button btn;
	private ListView lv;
	private List<Word> words;
	private WordAdapter adapter;
	//头部提示文字
	private TextView tvTitle;
	//编辑按钮
	private Button btnEdit;
	//删除按钮
	private Button btnCancel;
	//包含两个按钮的线性布局
	private LinearLayout llButtons;
	
	int actionPosition;
	//操作的单词信息
	private Word actionWord;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etEn=(EditText) findViewById(R.id.et_word_en);
		etZh=(EditText) findViewById(R.id.et_word_zh);
		btn=(Button) findViewById(R.id.btn_add);
		lv=(ListView) findViewById(R.id.lv_words);
		tvTitle=(TextView)findViewById(R.id.tv_title);
		btn.setOnClickListener(this);
		words=new WordDao(this).query(null,null);
		adapter=new WordAdapter(this, words);
		lv.setAdapter(adapter);
	    tvTitle=(TextView) findViewById(R.id.tv_title);
	    btnEdit=(Button) findViewById(R.id.btn_edit);
	    btnCancel=(Button) findViewById(R.id.btn_cancle);
	    llButtons=(LinearLayout) findViewById(R.id.ll_edit_buttons);
	    //为按钮配置监听器
	    btnCancel.setOnClickListener(this);
	    btnEdit.setOnClickListener(this);
		//为ListView注册ContextMenu
		registerForContextMenu(lv);
	}
	public static final int MENU_ID_EDIT=1;
	public static final int MENU_ID_DELETE=2;
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		//获取操作的菜单信息
		AdapterContextMenuInfo info=(AdapterContextMenuInfo) menuInfo;
		//根据菜单信息中的position获取操作的数据
		actionPosition=info.position;
		actionWord=words.get(info.position);
		//添加菜单项
		menu.add(Menu.NONE, MENU_ID_EDIT,1,"编辑("+actionWord.getEn()+")");
		menu.add(Menu.NONE,MENU_ID_DELETE, 2, "删除("+actionWord.getZh()+")");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case MENU_ID_EDIT:
			//更换标题文字
			tvTitle.setText("编辑单词");
			//将被编辑的数据显示在控件上
			etZh.setText(actionWord.getZh());
			etEn.setText(actionWord.getEn());
			//英文编辑框不可用
			etEn.setEnabled(false);
			//添加按钮不可见
			btn.setVisibility(View.GONE);
			//两个按钮可以见
			llButtons.setVisibility(View.VISIBLE);
			break;
		case MENU_ID_DELETE:
			//弹出确认对话框
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			AlertDialog dialog=builder.setTitle("警告")
					.setMessage("删除操作不可恢复，你确定要删除"
							+actionWord.getEn()+"数据吗？")
							.setPositiveButton("确定",this)
									.setNegativeButton("取消",this).create();
			dialog.show();
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_add:
			//获取控件中的输入内容
			String en=etEn.getText().toString();
			String zh=etZh.getText().toString();
			//把用户输入的内容封装成Word对象
			Word word=new Word(en,zh);
			//创建DAO对象
			IDao dao=new WordDao(this);
			
			//调用dao的添加数据的方法，并获取结果
			long id=dao.insert(word);
			//对结果进行响应
			if(id==-1){
				Toast.makeText(this,"添加数据失败",Toast.LENGTH_SHORT).show();
			}else{
				//提示添加成功
				Toast.makeText(this,"添加数据成功！ID="+id, Toast.LENGTH_SHORT).show();
				//清空输入框
				etEn.setText(null);
				etZh.setText(null);
				//刷新列表
				word.setId(id);
				words.add(0,word);
				adapter.notifyDataSetChanged();
			}
			break;
		case R.id.btn_cancle:
			setAddMode();
			break;
		case R.id.btn_edit:
			//从控件中获取数据
			String _zh=etZh.getText().toString().trim();
			//执行修改
			actionWord.setZh(_zh);
			int affectedRows=new WordDao(this).update(actionWord);
			//相应操作结果
			if(affectedRows==0){
				Toast.makeText(this,"修改失败！！！",Toast.LENGTH_SHORT).show();
			}else{
				//刷新列表
				words.set(actionPosition, actionWord);
				adapter.notifyDataSetChanged();
				//还原为添加模式
				setAddMode();
			}
			break;
		}
		
	}
	

	private void setAddMode() {
		//添加模式
		//清空输入框控件
		etEn.setText(null);
		etZh.setText(null);
		//更新头部文字显示
		tvTitle.setText("添加单词信息");
		//英文编辑框可用
		etEn.setEnabled(true);
		//添加按钮不可见
		btn.setVisibility(View.VISIBLE);
		//两个按钮不可见
		llButtons.setVisibility(View.GONE);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch(which){
		case DialogInterface.BUTTON_POSITIVE:
			int affectRows=new WordDao(this).delete(actionWord.getId());
			//根据结果提示或刷新界面
			if(affectRows==0){
				//提示失败
				Toast.makeText(this,"删除失败", Toast.LENGTH_SHORT).show();
				
			}else{
				//刷新界面
				words.remove(actionWord);
				adapter.notifyDataSetChanged();
			}
			break;
		}
	}

}



















