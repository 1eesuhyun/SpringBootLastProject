package com.sist.web.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

@RestController
@RequestMapping("/jeju/")
@RequiredArgsConstructor
public class JejuRestController {
	private final JejuService jservice;
	
	@GetMapping("find_vue/")
	public ResponseEntity<Map> jeju_find_vue(@RequestParam("page")int page,@RequestParam("fd") String fd,@RequestParam("selected") int selected)
	{
		Map map=new HashMap();
		try
		{
			map.put("start", (page-1)*12);
			map.put("selected", selected);
			map.put("fd", fd);
			List<JejuVO> list=jservice.jejuFindData(map);
			int totalpage=jservice.jejuFindTotalPage(map);
			for(JejuVO vo:list)
			{
				String[] addrs=vo.getAddress().split(" ");
				vo.setAddress(addrs[1]+" "+addrs[2]);
			}
			final int BLOCK=5;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map=new HashMap();
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("endPage", endPage);
			map.put("startPage", startPage);
			map.put("selected", selected);
			map.put("fd", fd);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
