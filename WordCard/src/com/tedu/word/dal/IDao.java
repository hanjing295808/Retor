package com.tedu.word.dal;

import java.util.List;

import com.tedu.word.entity.Word;

public interface IDao {
	//插入数据
	long insert(Word word);
	//查询数据
	List<Word> query(String whereClause,String[] whereArgs);
	//删除数据集
	int delete(long id);
	//更新数据
	int update(Word word);
}
