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

	public long getCount() {
		// TODO Auto-generated method stub
		return boardRepository.getCount();
	}

	public long getTotalPage(long total) {
		long totalpage=(total%10==0)? total/10:total/10+1;
		// TODO Auto-generated method stub
		return totalpage;
	}

	public int getIdx(int page) {
		// TODO Auto-generated method stub
		return page/5+1;
		
	}
	
	public long getLastIdx(long total) {
		long lastidx=(total%50==0)? total/50+1:total/50+2;
		return lastidx;
	}

	public int getPage(String move, long totalpage, int page) {
		if(move!=null && "u".equals(move)) {
			page+=5;
			if(page>totalpage) {
				page=(int) totalpage;
			}
		}
		if(move!=null && "d".equals(move)) {
			if(page-5<1) {
				page=1;
			}
			else {
				page=page-5;
			}
			
		}
		System.out.println(page);
		return page;
	}

	public void insert(BoardVo vo) {
		vo.setG_no(boardRepository.getAuto());
		boardRepository.newinsert(vo);
	}

	public BoardVo findByNo(long no) {
		return boardRepository.findByNo(no);
		
	}

	public boolean reinsert(BoardVo vo) {
		// TODO Auto-generated method stub
		long child=boardRepository.getGorderByP(vo.getParent())+1;
		vo.setG_order(Math.max(vo.getG_order(), child));
		return boardRepository.reinsert(vo);
		
	}

}
