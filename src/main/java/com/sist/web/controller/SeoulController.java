package com.sist.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;
/*
 *  MVC : 오라클 / 컨트롤러 / JSP
 *        ------------- Vue / React
 *        => SQL / 사용작 어떤 데이터를 보낼지
 */
@Controller
@RequiredArgsConstructor
public class SeoulController {
	private final SeoulService sservice;
	
	@GetMapping("/seoul/location")
	public String seoul_lotacion(@RequestParam(name="page",required = false) String page,Model model)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		List<SeoulVO> list=sservice.seoulLocationListData((curpage-1)*12);
		int totalpage=sservice.seoulLocationTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		for(SeoulVO vo:list)
		{
			String[] addrs=vo.getAddress().split(" ");
			vo.setAddress(addrs[0]+" "+addrs[1]);
			String s=vo.getLvo().getUsetime();
			int i=s.indexOf("(");
			if(i>=0)
			{
				vo.getLvo().setUsetime(s.substring(0,i).trim());
			}
		}
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		
		model.addAttribute("main_jsp", "../seoul/location.jsp");
		// include가 되는 파일을 올린다 => request를 공유할 수 있다
		return "main/main";
	}
}
