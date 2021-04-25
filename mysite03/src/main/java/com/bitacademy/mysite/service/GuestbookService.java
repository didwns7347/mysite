package com.bitacademy.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.GuestbookRepository;
import com.bitacademy.mysite.vo.GuestbookVo;
@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> findAll() {
		List<GuestbookVo> list=guestbookRepository.findAll();
		for(GuestbookVo vo:list) {
			System.out.println(vo.toString());
		}
		return list;
	}

	public void delete(GuestbookVo vo) {
		System.out.println(vo.toString());
		guestbookRepository.delete(vo);
		
	}

	public void insert(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		
	}

	

}
