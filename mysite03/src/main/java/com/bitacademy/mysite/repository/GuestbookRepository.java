package com.bitacademy.mysite.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.GuestbookVo;


@Repository
public class GuestbookRepository {
	@Autowired
	private SqlSession sqlSession;
	

	public boolean delete(GuestbookVo vo) {
		int count = sqlSession.delete("guestbook.delete",vo);
		System.out.println(count);
		return count==1;	
	}

	public boolean insert(GuestbookVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		System.out.println(count);
		return count==1;
		
	}

	public List<GuestbookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}

	
}