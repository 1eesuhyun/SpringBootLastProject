package com.sist.web.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;

import com.sist.web.service.*;

@RestController
@RequiredArgsConstructor
public class ReserveRestController {
	private final ReserveService rservice;
	private final SimpMessagingTemplate template;
	private final String[] times= {
			"09:00","10:00","11:00","12:00","12:30","13:00","13:30","14:00","15:00","16:00","17:00","18:00","18:30","19:00","20:00","20:00","20:30","21:00","22:00"
	};
	public List<String> timeRand()
	{
		List<String> res=new ArrayList<String>();
		int[] com=new int[(int)(Math.random()*6)+5];
		boolean bClick=false;
		int su=0;
		for(int i=0;i<com.length;i++)
		{
			bClick=true;
			while(bClick)
			{
				su=(int)(Math.random()*18);
				bClick=false;
				for(int j=0;j<i;j++)
				{
					if(com[j]==su)
					{
						bClick=true;
						break;
					}
				}
			}
			com[i]=su;
		}
		Arrays.sort(com);
		for(int i=0;i<com.length;i++)
		{
			res.add(times[com[i]]);
		}
		return res;
	}
	
	@GetMapping("/reserve/food_list_vue/")
	public ResponseEntity<Map> food_list(@RequestParam("address") String address,@RequestParam("page") int page)
	{
		Map map=new HashMap();
		try
		{
			map.put("address", address);
			map.put("start", (page-1)*8);
			List<SeoulVO> list=rservice.seoulReserveData(map);
			int totalpage=rservice.seoulReserveTotalPage(map);
			
			map=new HashMap<>();
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("address", address);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	/*
	 *  Vue
	 *   => mounted => onMounted(()=>{})
	 *   => watch => 설정된 데이터 변경시에만 호출
	 *   => computed => 완성된 데이터
	 *   => getters / actions / state
	 */
	@GetMapping("/reserve/time_list_vue/")
	public ResponseEntity<Map> time_list()
	{
		Map map=new HashMap();
		try
		{
			List<String> list=timeRand();
			map.put("list", list);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/reserve/insert_vue/")
	public ResponseEntity<String> insert_vue(@RequestBody ReserveVO vo,HttpSession session)
	{
		String res="";
		try
		{
			String id=(String)session.getAttribute("userid");
			vo.setId(id);
			res=rservice.reserveInsert(vo);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@GetMapping("/mypage/reserve_list/")
	public ResponseEntity<List<ReserveVO>> reserve_list(HttpSession session)
	{
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		try
		{
			String id=(String)session.getAttribute("userid");
			list=rservice.myReserveList(id);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/admin/reserve_list/")
	public ResponseEntity<List<ReserveVO>> reserve_list()
	{
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		try
		{
			list=rservice.adminReserveList();
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/admin/reserve_ok_vue/")
	public ResponseEntity<List<ReserveVO>> admin_reserve(@RequestParam("no") int no,@RequestParam("id") String id)
	{
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		try
		{
			rservice.reserveOk(no);
			list=rservice.adminReserveList();
			
			template.convertAndSend("/sub/notice/"+id,"[🤡예약 승인🤡] 예약이 완료되었습니다");
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/mypage/reserve_cancel_vue/")
	public ResponseEntity<List<ReserveVO>> mypage_reserve_cancel(@RequestParam("no") int no,HttpSession session)
	{
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		try
		{
			rservice.reserveCancel(no);
			String id=(String)session.getAttribute("userid");
			list=rservice.myReserveList(id);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	} 
	@GetMapping("/admin/reserve_delete_vue/")
	public ResponseEntity<List<ReserveVO>> admin_reserve_delete(@RequestParam("no") int no,@RequestParam("id") String id)
	{
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		try
		{
			rservice.reserveDelete(no);
			list=rservice.adminReserveList();
			
			template.convertAndSend("/sub/notice/"+id,"[🤡예약 취소🤡] 예약이 취소되었습니다");
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	} 
	@GetMapping("/mypage/reserve_detail_vue/")
	public ResponseEntity<ReserveVO> mypage_reserve_detail(@RequestParam("no") int no)
	{
		ReserveVO vo=new ReserveVO();
		try
		{
			vo=rservice.reserveDetailData(no);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
}
