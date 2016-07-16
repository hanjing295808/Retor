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
		//准备返回值
		long id=-1;
		//获取SQLiteDatabase对象
		DBOpenHelper dbOpenHelper=new DBOpenHelper(context);
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		
		//执行添加数据，并获取结果
		String nullColumnHack=Database.Word.Columns._ID;
		ContentValues values=new ContentValues();
		values.put(Database.Word.Columns.EN, word.getEn());
		values.put(Database.Word.Columns.ZH,word.getZh());
		id=db.insert(Database.Word.TABLE_NAME, nullColumnHack, values);
		db.close();
		//返回
		return id;
	}

	@Override
	public List<Word> query(String whereClause, String[] whereArgs) {
		// 准备返回值
		List<Word> words=new ArrayList<Word>();
		//获取SQLiteDatabase对象
		DBOpenHelper helper=new DBOpenHelper(context);
		SQLiteDatabase db=helper.getWritableDatabase();
		//执行查询数据，并获取结果
		String[] columns={
			Database.Word.Columns._ID, //0
			Database.Word.Columns.EN,  //1
			Database.Word.Columns.ZH   //2
		};
		Cursor c=db.query(Database.Word.TABLE_NAME, columns, whereClause, whereArgs, null, null, Database.Word.Columns._ID+" DESC");
		//分析结果
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			Word word=new Word();
			word.setId(c.getLong(0));
			word.setEn(c.getString(1));
			word.setZh(c.getString(2));
			words.add(word);
		}
		
		//释放资源
		db.close();
		return words;
	}

	@Override
	public int delete(long id) {
		//准备返回值
		int affectedRows=0;
		//获取SQLiteDatabase对象
		DBOpenHelper dbOpenHelper=new DBOpenHelper(context);
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		
		//执行删除数据，并获取结果
		String table=Database.Word.TABLE_NAME;
		String whereClause=Database.Word.Columns._ID+"=?";
		String[] whereArgs={id+""};
		affectedRows=db.delete(table, whereClause, whereArgs);
		//释放资源
		db.close();
		//返回
		return affectedRows;
	}

	@Override
	public int update(Word word) {
		//准备返回值
		int affectedRows=0;
		
		//获取SQLiteDatabase对象
		DBOpenHelper dbOpenHelper=new DBOpenHelper(context);
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		
		//执行更新数据，并获取结果
		String table=Database.Word.TABLE_NAME;
		ContentValues values=new ContentValues();
		values.put(Database.Word.Columns.ZH,word.getZh());
		String whereClause=Database.Word.Columns._ID+"=?";
		String[] whereArgs={word.getId()+""};
		affectedRows=db.update(table, values, whereClause, whereArgs);
		
		//释放资源
		db.close();
		
		//返回
		return affectedRows;
	}
	
}















