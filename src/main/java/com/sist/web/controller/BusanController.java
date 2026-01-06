package com.sist.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sist.web.service.BusanService;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;

@Controller
@RequiredArgsConstructor
public class BusanController {
	private final BusanService bservice;
	
	@GetMapping("/busan/list")
	public String busan_lotacion(@RequestParam(name="page",required = false) String page,Model model,@RequestParam("cno") int cno)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage-1)*12);
		map.put("contenttype", cno);
		List<BusanVO> list=bservice.busanListData(map);
		for(BusanVO vo:list)
		{
			String[] addrs=vo.getAddress().split(" ");
			vo.setAddress(addrs[1]+" "+addrs[2]);
		}
		int totalpage=bservice.busanTotalPage(cno);
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		String name = "";

		switch (cno) {
		    case 12:
		        name = "부산 관광지";
		        break;
		    case 14:
		        name = "부산 문화시설";
		        break;
		    case 15:
		        name = "부산 축제 & 공연";
		        break;
		    case 32:
		        name = "부산 숙박";
		        break;
		    case 38:
		        name = "부산 쇼핑";
		        break;
		    case 39:
		        name = "부산 음식";
		        break;
		    default:
		        name = "";
		}
		model.addAttribute("name", name);
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("cno", cno);
		
		model.addAttribute("main_jsp", "../busan/list.jsp");
		// include가 되는 파일을 올린다 => request를 공유할 수 있다
		return "main/main";
	}
	
	@GetMapping("/busan/find")
	public String busan_find(Model model)
	{
		model.addAttribute("main_jsp", "../busan/busan_find.jsp");
		return "main/main";
	}
}
