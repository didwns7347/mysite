package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.security.AuthUser;
import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.GuestbookVo;
import com.bitacademy.mysite.vo.UserVo;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@RequestMapping("")
	public String index(int page,String move,Model model) {
		long total = boardService.getCount();
		long totalpage=boardService.getTotalPage(total);
		page=boardService.getPage(move,totalpage,page);
		int lastidx=(int) boardService.getLastIdx(totalpage);
		int idx = boardService.getIdx(page);
		
		List<BoardVo> list = boardService.findPage(page);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("page",page);
		model.addAttribute("list", list);
		model.addAttribute("index",idx);
		model.addAttribute("total",total);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("lastidx",lastidx);
		return "board/index";
	}
	
	@RequestMapping(value="/write" , method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write" , method=RequestMethod.POST)
	public String write(@AuthUser UserVo authUser,BoardVo vo) {
		vo.setWriter(authUser.getName());
		boardService.insert(vo);
		return "redirect:/board?page=1";	
	}
	@RequestMapping(value="/rewriteform" )
	public String rewriteform(BoardVo vo,Model model,long page) {
		model.addAttribute("boardvo", vo);
		model.addAttribute("page",page);
		return "board/rewrite";	
	}
	@Auth
	@RequestMapping(value="/rewrite" , method=RequestMethod.POST)
	public String rewrite(BoardVo vo,Model model,@AuthUser UserVo authUser,long page) {
		vo.setWriter(authUser.getName());
		boardService.reinsert(vo);
		
		return "redirect:/board?page="+page;	
	}
	
	@RequestMapping(value="/view" , method=RequestMethod.GET)
	public String view(long no,long page, Model model) {
		BoardVo vo = boardService.findByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		return "board/view";	
	}
	
	
	
	
}
