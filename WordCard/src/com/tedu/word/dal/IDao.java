package com.tedu.word.dal;

import java.util.List;

import com.tedu.word.entity.Word;

public interface IDao {
	//��������
	long insert(Word word);
	//��ѯ����
	List<Word> query(String whereClause,String[] whereArgs);
	//ɾ�����ݼ�
	int delete(long id);
	//��������
	int update(Word word);
}
