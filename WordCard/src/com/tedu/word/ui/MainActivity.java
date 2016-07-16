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
	//ͷ����ʾ����
	private TextView tvTitle;
	//�༭��ť
	private Button btnEdit;
	//ɾ����ť
	private Button btnCancel;
	//����������ť�����Բ���
	private LinearLayout llButtons;
	
	int actionPosition;
	//�����ĵ�����Ϣ
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
	    //Ϊ��ť���ü�����
	    btnCancel.setOnClickListener(this);
	    btnEdit.setOnClickListener(this);
		//ΪListViewע��ContextMenu
		registerForContextMenu(lv);
	}
	public static final int MENU_ID_EDIT=1;
	public static final int MENU_ID_DELETE=2;
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		//��ȡ�����Ĳ˵���Ϣ
		AdapterContextMenuInfo info=(AdapterContextMenuInfo) menuInfo;
		//���ݲ˵���Ϣ�е�position��ȡ����������
		actionPosition=info.position;
		actionWord=words.get(info.position);
		//��Ӳ˵���
		menu.add(Menu.NONE, MENU_ID_EDIT,1,"�༭("+actionWord.getEn()+")");
		menu.add(Menu.NONE,MENU_ID_DELETE, 2, "ɾ��("+actionWord.getZh()+")");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case MENU_ID_EDIT:
			//������������
			tvTitle.setText("�༭����");
			//�����༭��������ʾ�ڿؼ���
			etZh.setText(actionWord.getZh());
			etEn.setText(actionWord.getEn());
			//Ӣ�ı༭�򲻿���
			etEn.setEnabled(false);
			//��Ӱ�ť���ɼ�
			btn.setVisibility(View.GONE);
			//������ť���Լ�
			llButtons.setVisibility(View.VISIBLE);
			break;
		case MENU_ID_DELETE:
			//����ȷ�϶Ի���
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			AlertDialog dialog=builder.setTitle("����")
					.setMessage("ɾ���������ɻָ�����ȷ��Ҫɾ��"
							+actionWord.getEn()+"������")
							.setPositiveButton("ȷ��",this)
									.setNegativeButton("ȡ��",this).create();
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
			//��ȡ�ؼ��е���������
			String en=etEn.getText().toString();
			String zh=etZh.getText().toString();
			//���û���������ݷ�װ��Word����
			Word word=new Word(en,zh);
			//����DAO����
			IDao dao=new WordDao(this);
			
			//����dao��������ݵķ���������ȡ���
			long id=dao.insert(word);
			//�Խ��������Ӧ
			if(id==-1){
				Toast.makeText(this,"�������ʧ��",Toast.LENGTH_SHORT).show();
			}else{
				//��ʾ��ӳɹ�
				Toast.makeText(this,"������ݳɹ���ID="+id, Toast.LENGTH_SHORT).show();
				//��������
				etEn.setText(null);
				etZh.setText(null);
				//ˢ���б�
				word.setId(id);
				words.add(0,word);
				adapter.notifyDataSetChanged();
			}
			break;
		case R.id.btn_cancle:
			setAddMode();
			break;
		case R.id.btn_edit:
			//�ӿؼ��л�ȡ����
			String _zh=etZh.getText().toString().trim();
			//ִ���޸�
			actionWord.setZh(_zh);
			int affectedRows=new WordDao(this).update(actionWord);
			//��Ӧ�������
			if(affectedRows==0){
				Toast.makeText(this,"�޸�ʧ�ܣ�����",Toast.LENGTH_SHORT).show();
			}else{
				//ˢ���б�
				words.set(actionPosition, actionWord);
				adapter.notifyDataSetChanged();
				//��ԭΪ���ģʽ
				setAddMode();
			}
			break;
		}
		
	}
	

	private void setAddMode() {
		//���ģʽ
		//��������ؼ�
		etEn.setText(null);
		etZh.setText(null);
		//����ͷ��������ʾ
		tvTitle.setText("��ӵ�����Ϣ");
		//Ӣ�ı༭�����
		etEn.setEnabled(true);
		//��Ӱ�ť���ɼ�
		btn.setVisibility(View.VISIBLE);
		//������ť���ɼ�
		llButtons.setVisibility(View.GONE);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch(which){
		case DialogInterface.BUTTON_POSITIVE:
			int affectRows=new WordDao(this).delete(actionWord.getId());
			//���ݽ����ʾ��ˢ�½���
			if(affectRows==0){
				//��ʾʧ��
				Toast.makeText(this,"ɾ��ʧ��", Toast.LENGTH_SHORT).show();
				
			}else{
				//ˢ�½���
				words.remove(actionWord);
				adapter.notifyDataSetChanged();
			}
			break;
		}
	}

}



















