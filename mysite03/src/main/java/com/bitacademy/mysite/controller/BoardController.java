package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@RequestMapping("")
	public String index(int page,String index,String move,Model model) {
		System.out.println("index");
		System.out.println("page="+page);
		List<BoardVo> list = boardService.findPage(page);
		int total = boardService.getCount();
		int totalpage=boardService.getTotalPage(total);
		for(BoardVo vo : list) {
			System.out.println(vo);
		}
		int lastidx=boardService.getLastIdx(totalpage);
		int idx = boardService.getIdx(index,lastidx);
		
		page=boardService.getPage(move,total,page);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list", list);
		model.addAttribute("index",idx);
		model.addAttribute("total",total);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("lastidx",lastidx);
		return "board/index";
	}
}
