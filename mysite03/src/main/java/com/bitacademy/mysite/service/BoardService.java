package com.bitacademy.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;
@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> findPage(int page) {
		// TODO Auto-generated method stub
		return boardRepository.findPage(page);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return boardRepository.getCount();
	}

	public int getTotalPage(int total) {
		int totalpage=(total%10==0)? total/10:total/10+1;
		// TODO Auto-generated method stub
		return totalpage;
	}

	public int getIdx(String index, int lastidx) {
		// TODO Auto-generated method stub
		int idx;
		if (index==null) {
			return 1;
		}
		else {
			idx=Integer.parseInt(index);
			System.out.println(idx);
			if(idx<1)
				idx=1;
			else if(idx>lastidx)
				idx=lastidx;
			return idx;
		}
		
	}
	
	public int getLastIdx(int total) {
		int lastidx=(total%50==0)? total/50+1:total/50+2;
		return lastidx;
	}

	public int getPage(String move, int total, int page) {
		if(move!=null && "u".equals(move)) {
			page++;
			if(page>total) {
				page=total;
			}
		}
		if(move!=null && "u".equals(move)) {
			if(page-5<1) {
				page=1;
			}
			else {
				page=(page-5)/5+4;
			}
			if(page>total) {
				page=1;
			}
		}
		return page;
	}

}
