package com.sist.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.JejuService;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;

@Controller
@RequiredArgsConstructor
public class JejuController {
	private final JejuService jservice;
	
	@GetMapping("jeju/list")
	public String jeju_list(@RequestParam(name="page",required = false) String page,@RequestParam("cno")int cno,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage-1)*12);
		map.put("contenttype", cno);
		List<JejuVO> list=jservice.jejuListData(map);
		for(JejuVO vo:list)
		{
			String[] addrs=vo.getAddress().split(" ");
			vo.setAddress(addrs[1]+" "+addrs[2]);
		}
		int totalpage=jservice.jejuTotalPage(cno);
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		String name = "";

		switch (cno) {
		    case 12:
		        name = "제주 관광지";
		        break;
		    case 14:
		        name = "제주 문화시설";
		        break;
		    case 15:
		        name = "제주 축제 & 공연";
		        break;
		    case 32:
		        name = "제주 숙박";
		        break;
		    case 38:
		        name = "제주 쇼핑";
		        break;
		    case 39:
		        name = "제주 음식";
		        break;
		    default:
		        name = "";
		}
		model.addAttribute("name", name);
		
		model.addAttribute("list", list);
		model.addAttribute("cno", cno);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		
		model.addAttribute("main_jsp", "../jeju/list.jsp");
		
		return "main/main";
	}
	@GetMapping("/jeju/find")
	public String jeju_find(Model model)
	{
		model.addAttribute("main_jsp", "../jeju/jeju_find.jsp");
		return "main/main";
	}
}
