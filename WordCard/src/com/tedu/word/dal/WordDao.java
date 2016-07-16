package com.tedu.word.dal;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tedu.word.entity.Word;
import com.tedu.word.util.DBOpenHelper;
import com.tedu.word.util.Database;

public class WordDao implements IDao{

	private Context context;
	public WordDao(Context context){
		this.context=context;
	}

	@Override
	public long insert(Word word) {
		//׼������ֵ
		long id=-1;
		//��ȡSQLiteDatabase����
		DBOpenHelper dbOpenHelper=new DBOpenHelper(context);
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		
		//ִ��������ݣ�����ȡ���
		String nullColumnHack=Database.Word.Columns._ID;
		ContentValues values=new ContentValues();
		values.put(Database.Word.Columns.EN, word.getEn());
		values.put(Database.Word.Columns.ZH,word.getZh());
		id=db.insert(Database.Word.TABLE_NAME, nullColumnHack, values);
		db.close();
		//����
		return id;
	}

	@Override
	public List<Word> query(String whereClause, String[] whereArgs) {
		// ׼������ֵ
		List<Word> words=new ArrayList<Word>();
		//��ȡSQLiteDatabase����
		DBOpenHelper helper=new DBOpenHelper(context);
		SQLiteDatabase db=helper.getWritableDatabase();
		//ִ�в�ѯ���ݣ�����ȡ���
		String[] columns={
			Database.Word.Columns._ID, //0
			Database.Word.Columns.EN,  //1
			Database.Word.Columns.ZH   //2
		};
		Cursor c=db.query(Database.Word.TABLE_NAME, columns, whereClause, whereArgs, null, null, Database.Word.Columns._ID+" DESC");
		//�������
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			Word word=new Word();
			word.setId(c.getLong(0));
			word.setEn(c.getString(1));
			word.setZh(c.getString(2));
			words.add(word);
		}
		
		//�ͷ���Դ
		db.close();
		return words;
	}

	@Override
	public int delete(long id) {
		//׼������ֵ
		int affectedRows=0;
		//��ȡSQLiteDatabase����
		DBOpenHelper dbOpenHelper=new DBOpenHelper(context);
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		
		//ִ��ɾ�����ݣ�����ȡ���
		String table=Database.Word.TABLE_NAME;
		String whereClause=Database.Word.Columns._ID+"=?";
		String[] whereArgs={id+""};
		affectedRows=db.delete(table, whereClause, whereArgs);
		//�ͷ���Դ
		db.close();
		//����
		return affectedRows;
	}

	@Override
	public int update(Word word) {
		//׼������ֵ
		int affectedRows=0;
		
		//��ȡSQLiteDatabase����
		DBOpenHelper dbOpenHelper=new DBOpenHelper(context);
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		
		//ִ�и������ݣ�����ȡ���
		String table=Database.Word.TABLE_NAME;
		ContentValues values=new ContentValues();
		values.put(Database.Word.Columns.ZH,word.getZh());
		String whereClause=Database.Word.Columns._ID+"=?";
		String[] whereArgs={word.getId()+""};
		affectedRows=db.update(table, values, whereClause, whereArgs);
		
		//�ͷ���Դ
		db.close();
		
		//����
		return affectedRows;
	}
	
}















