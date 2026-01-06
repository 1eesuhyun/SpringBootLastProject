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

// responseentity 비동기
@RestController
@RequiredArgsConstructor
@RequestMapping("/seoul/") // 공통 uri주소
public class SeoulRestController {
	private final SeoulService sservice;
	
	/*
	 *  구현 => mapper
	 *  통합 => service
	 *  브라우저로 전송 : request(model) => Controller
	 *                JSON => RestController => ResponseBody
	 *  웹 개발 : SpringFramework => security
	 *  => AI : websocket / kafka / batch / task
	 *     -- Vue (Nuxt) / React(Next) => fullstack
	 */
	@GetMapping("find_vue/")
	public ResponseEntity<Map> find_vue(@RequestParam("page") int page,@RequestParam("address") String address)
	{
		Map map=new HashMap();
		try
		{
			map.put("start", (page-1)*12);
			map.put("address", address);
			List<SeoulVO> list=sservice.seoulFindData(map);
			for(SeoulVO vo:list)
			{
				String[] addrs=vo.getAddress().split(" ");
				vo.setAddress(addrs[1]+" "+addrs[2]);
			}
			int totalpage=sservice.seoulFindTotalPage(address);
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			// JSON으로 묶어서 브라우저로 전송
			map=new HashMap();
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("endPage", endPage);
			map.put("startPage", startPage);
			map.put("address", address);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			// HttpStatus.INTERNAL_SERVER_ERROR) => error를 브라우저로 전송
		}
		return new ResponseEntity<>(map,HttpStatus.OK); // ok:200 => 정상수행
	}
}
